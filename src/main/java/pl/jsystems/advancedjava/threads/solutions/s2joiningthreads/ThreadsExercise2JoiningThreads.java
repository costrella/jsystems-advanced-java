package pl.jsystems.advancedjava.threads.solutions.s2joiningthreads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.threads.solutions.s2joiningthreads.contents.CargoLoadedMessageContent;
import pl.jsystems.advancedjava.threads.solutions.s2joiningthreads.message.Message;
import pl.jsystems.advancedjava.threads.solutions.s2joiningthreads.receivers.CargoLoadedMessageReceiver;
import pl.jsystems.advancedjava.threads.solutions.s2joiningthreads.receivers.CargoUnloadedMessageReceiver;
import pl.jsystems.advancedjava.threads.solutions.s2joiningthreads.receivers.GPSTrackingMessageReceiver;
import pl.jsystems.advancedjava.threads.solutions.s2joiningthreads.repositories.CargoLoadedMessageRepository;
import pl.jsystems.advancedjava.threads.solutions.s2joiningthreads.repositories.CargoUnloadedMessageRepository;
import pl.jsystems.advancedjava.threads.solutions.s2joiningthreads.repositories.GPSTrackingMessageRepository;

import java.time.Instant;
import java.util.List;

class ThreadsExercise2JoiningThreads
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsExercise2JoiningThreads.class);

    public static void main(String[] args)
    {
        new ThreadsExercise2JoiningThreads().run();
    }

    private static final GPSTrackingMessageRepository GPS_TRACKING_MESSAGE_REPOSITORY = new GPSTrackingMessageRepository();
    private static final CargoLoadedMessageRepository CARGO_LOADED_MESSAGE_REPOSITORY = new CargoLoadedMessageRepository();
    private static final CargoUnloadedMessageRepository CARGO_UNLOADED_MESSAGE_REPOSITORY = new CargoUnloadedMessageRepository();

    private void run()
    {
        long startTime = Instant.now().toEpochMilli();
        LOGGER.info("Multithreading...");
        MessageLogger messageLogger = new MessageLogger();
        receiveAndStore(messageLogger);

        List<Message<CargoLoadedMessageContent>> cargoLoadedMessages = CARGO_LOADED_MESSAGE_REPOSITORY.findAll();

        // those logs are not ok - we'll fix that soon!
        LOGGER.info("All messages - size {}: {}", cargoLoadedMessages.size(), cargoLoadedMessages);
        long endTime = Instant.now().toEpochMilli();
        LOGGER.info("Time spent processing (ms): {}", (endTime - startTime));
    }


    private static void receiveAndStore(MessageLogger messageLogger)
    {
        Thread thread1 = new GPSTrackingMessageReceiver().startReceivingUsing(message ->
        {
            messageLogger.logReceived(message);
            GPS_TRACKING_MESSAGE_REPOSITORY.save(message);
            LOGGER.info("Saved GPS message: {}", message);
        });

        Thread thread2 = new CargoLoadedMessageReceiver().startReceivingUsing(message ->
        {
            messageLogger.logReceived(message);
            CARGO_LOADED_MESSAGE_REPOSITORY.save(message);
            LOGGER.info("Saved Cargo Loaded message: {}", message);
        });

        Thread thread3 = new CargoUnloadedMessageReceiver().startReceivingUsing(message ->
        {
            messageLogger.logReceived(message);
            CARGO_UNLOADED_MESSAGE_REPOSITORY.save(message);
            LOGGER.info("Saved Cargo Unloaded message: {}", message);
        });

        joinThread(thread1);
        joinThread(thread2);
        joinThread(thread3);
    }

    private static void joinThread(final Thread thread)
    {
        try
        {
            thread.join();
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread has been interrupted while waiting to finish receiving new message.", e);
        }
    }
}
