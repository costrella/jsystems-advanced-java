package pl.jsystems.advancedjava.threads.solutions.s7bonusrefactoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.threads.solutions.s7bonusrefactoring.contents.MessageContent;
import pl.jsystems.advancedjava.threads.solutions.s7bonusrefactoring.message.Message;

import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;

class MessageRepositoryWorkerThread<T extends MessageContent> extends Thread
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageRepositoryWorkerThread.class);

    private final BlockingQueue<Message<T>> queue;
    private final Consumer<Message<T>> consumer;

    MessageRepositoryWorkerThread(BlockingQueue<Message<T>> queue, Consumer<Message<T>> consumer)
    {
        this.queue = queue;
        this.consumer = consumer;
    }

    @Override
    public void run()
    {
        LOGGER.info("Thread started.");
        while (true)
        {
            LOGGER.info("Waiting for a new message...");
            Message<T> newMessage;
            try
            {
                newMessage = queue.take();
            } catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
                LOGGER.error("Interrupted while waiting for a message!", e);
                throw new RuntimeException("Interrupted while waiting for a message!", e);
            }
            LOGGER.info("New message acquired!");
            consumer.accept(newMessage);
        }
    }
}
