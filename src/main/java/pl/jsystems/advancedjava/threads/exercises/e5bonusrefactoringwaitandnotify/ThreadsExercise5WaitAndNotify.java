package pl.jsystems.advancedjava.threads.exercises.e5bonusrefactoringwaitandnotify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.threads.exercises.e5bonusrefactoringwaitandnotify.contents.CargoLoadedMessageContent;
import pl.jsystems.advancedjava.threads.exercises.e5bonusrefactoringwaitandnotify.message.Message;
import pl.jsystems.advancedjava.threads.exercises.e5bonusrefactoringwaitandnotify.receivers.CargoLoadedMessageReceiver;
import pl.jsystems.advancedjava.threads.exercises.e5bonusrefactoringwaitandnotify.repositories.CargoLoadedMessageRepository;

import java.time.Instant;
import java.util.List;

class ThreadsExercise5WaitAndNotify
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsExercise5WaitAndNotify.class);

    public static void main(String[] args)
    {
        new ThreadsExercise5WaitAndNotify().run();
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
