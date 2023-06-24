package pl.jsystems.advancedjava.threads.solutions.s3sleepandinterruption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.threads.solutions.s3sleepandinterruption.contents.CargoLoadedMessageContent;
import pl.jsystems.advancedjava.threads.solutions.s3sleepandinterruption.message.Message;
import pl.jsystems.advancedjava.threads.solutions.s3sleepandinterruption.receivers.CargoLoadedMessageReceiver;
import pl.jsystems.advancedjava.threads.solutions.s3sleepandinterruption.repositories.CargoLoadedMessageRepository;
import pl.jsystems.advancedjava.threads.solutions.s3sleepandinterruption.repositories.CargoUnloadedMessageRepository;
import pl.jsystems.advancedjava.threads.solutions.s3sleepandinterruption.repositories.GPSTrackingMessageRepository;

import java.time.Instant;
import java.util.List;

class ThreadsExercise3SleepAndInterruption
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsExercise3SleepAndInterruption.class);

    public static void main(String[] args)
    {
        new ThreadsExercise3SleepAndInterruption().run();
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
        Thread thread = new CargoLoadedMessageReceiver().startReceivingUsing(message ->
                new Thread(() ->
                {
                    messageLogger.logReceived(message);
                    CARGO_LOADED_MESSAGE_REPOSITORY.save(message);
                    LOGGER.info("Saved Cargo Loaded message: {}", message);
                }).start()
        );

        joinThread(thread);
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
