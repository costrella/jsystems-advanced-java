package pl.jsystems.advancedjava.threads.solutions.s5bonusrefactoringwaitandnotify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.threads.solutions.s5bonusrefactoringwaitandnotify.contents.CargoLoadedMessageContent;
import pl.jsystems.advancedjava.threads.solutions.s5bonusrefactoringwaitandnotify.message.Message;

import java.util.function.Consumer;

class CargoLoadedMessageRepositoryWorkerThread extends Thread
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CargoLoadedMessageRepositoryWorkerThread.class);

    private final CargoLoadedMessageRepositoryNotifier monitor;
    private final Consumer<Message<CargoLoadedMessageContent>> consumer;

    CargoLoadedMessageRepositoryWorkerThread(CargoLoadedMessageRepositoryNotifier monitor, Consumer<Message<CargoLoadedMessageContent>> consumer)
    {
        this.monitor = monitor;
        this.consumer = consumer;
    }

    @Override
    public void run()
    {
        LOGGER.info("Thread started.");
        while (true)
        {
            LOGGER.info("Waiting for a new message");
            var newMessage = monitor.waitForNewMessage();
            LOGGER.info("New message acquired!");
            consumer.accept(newMessage);
        }
    }
}
