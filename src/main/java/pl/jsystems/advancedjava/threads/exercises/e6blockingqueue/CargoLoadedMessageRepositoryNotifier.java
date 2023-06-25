package pl.jsystems.advancedjava.threads.exercises.e6blockingqueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.threads.exercises.e6blockingqueue.message.Message;
import pl.jsystems.advancedjava.threads.exercises.e6blockingqueue.contents.CargoLoadedMessageContent;

import java.util.ArrayList;
import java.util.List;

class CargoLoadedMessageRepositoryNotifier
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CargoLoadedMessageRepositoryWorkerThread.class);

    private final List<Message<CargoLoadedMessageContent>> queuedMessages = new ArrayList<>();

    synchronized void notifyAbout(final Message<CargoLoadedMessageContent> newMessage)
    {
        queuedMessages.add(newMessage);
        notifyAll();
    }

    synchronized Message<CargoLoadedMessageContent> waitForNewMessage()
    {
        if (queuedMessages.size() > 0)
        {
            return queuedMessages.remove(0);
        }

        do
        {
            waitingForNotification();
        } while(queuedMessages.size() == 0);
        return queuedMessages.remove(0);
    }

    private void waitingForNotification()
    {
        try
        {
            wait();
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            LOGGER.info("Interrupted!");
            throw new RuntimeException("Interrupted!", e);
        }
    }
}
