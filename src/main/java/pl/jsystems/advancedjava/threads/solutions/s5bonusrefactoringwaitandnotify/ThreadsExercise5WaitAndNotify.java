package pl.jsystems.advancedjava.threads.solutions.s5bonusrefactoringwaitandnotify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.threads.solutions.s5bonusrefactoringwaitandnotify.contents.CargoLoadedMessageContent;
import pl.jsystems.advancedjava.threads.solutions.s5bonusrefactoringwaitandnotify.message.Message;
import pl.jsystems.advancedjava.threads.solutions.s5bonusrefactoringwaitandnotify.receivers.CargoLoadedMessageReceiver;
import pl.jsystems.advancedjava.threads.solutions.s5bonusrefactoringwaitandnotify.repositories.CargoLoadedMessageRepository;

import java.time.Instant;
import java.util.List;
import java.util.function.Consumer;

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
        CargoLoadedMessageRepositoryNotifier notifier = new CargoLoadedMessageRepositoryNotifier();
        Consumer<Message<CargoLoadedMessageContent>> consumer = message ->
        {
            messageLogger.logReceived(message);
            CARGO_LOADED_MESSAGE_REPOSITORY.save(message);
            LOGGER.info("Saved Cargo Loaded message: {}", message);
        };

        Thread repositoryWorkerThread1 = new CargoLoadedMessageRepositoryWorkerThread(notifier, consumer);
        repositoryWorkerThread1.start();
        Thread repositoryWorkerThread2 = new CargoLoadedMessageRepositoryWorkerThread(notifier, consumer);
        repositoryWorkerThread2.start();
        Thread repositoryWorkerThread3 = new CargoLoadedMessageRepositoryWorkerThread(notifier, consumer);
        repositoryWorkerThread3.start();

        new CargoLoadedMessageReceiver().startReceivingUsing(notifier::notifyAbout);

        try
        {
            Thread.sleep(10000);
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            LOGGER.error("Interrupted!");
            throw new RuntimeException("Interrupted!", e);
        }

        repositoryWorkerThread1.interrupt();
        repositoryWorkerThread2.interrupt();
        repositoryWorkerThread3.interrupt();
    }
}
