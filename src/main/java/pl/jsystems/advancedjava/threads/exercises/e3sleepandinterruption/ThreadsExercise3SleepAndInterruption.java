package pl.jsystems.advancedjava.threads.exercises.e3sleepandinterruption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.threads.exercises.e3sleepandinterruption.contents.CargoLoadedMessageContent;
import pl.jsystems.advancedjava.threads.exercises.e3sleepandinterruption.message.Message;
import pl.jsystems.advancedjava.threads.exercises.e3sleepandinterruption.receivers.CargoLoadedMessageReceiver;
import pl.jsystems.advancedjava.threads.exercises.e3sleepandinterruption.repositories.CargoLoadedMessageRepository;

import java.time.Instant;
import java.util.List;

class ThreadsExercise3SleepAndInterruption
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsExercise3SleepAndInterruption.class);

    public static void main(String[] args)
    {
        new ThreadsExercise3SleepAndInterruption().run();
    }

    private static final CargoLoadedMessageRepository CARGO_LOADED_MESSAGE_REPOSITORY = new CargoLoadedMessageRepository();

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
        {
            messageLogger.logReceived(message);
            CARGO_LOADED_MESSAGE_REPOSITORY.save(message);
            LOGGER.info("Saved Cargo Loaded message: {}", message);
        });

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
            LOGGER.error("Thread has been interrupted while waiting to finish receiving new message.", e);
            throw new RuntimeException("Thread has been interrupted while waiting to finish receiving new message.", e);
        }
    }
}
