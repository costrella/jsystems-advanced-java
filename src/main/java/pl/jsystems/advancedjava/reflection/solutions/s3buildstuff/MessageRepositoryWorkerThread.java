package pl.jsystems.advancedjava.reflection.solutions.s3buildstuff;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.reflection.solutions.s3buildstuff.contents.MessageContent;
import pl.jsystems.advancedjava.reflection.solutions.s3buildstuff.message.Message;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

class MessageRepositoryWorkerThread<T extends MessageContent> implements Runnable
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageRepositoryWorkerThread.class);

    private final BlockingQueue<Message<T>> queue;
    private final Consumer<Message<T>> consumer;
    private final Executor executor;

    MessageRepositoryWorkerThread(BlockingQueue<Message<T>> queue, Executor executor, Consumer<Message<T>> consumer)
    {
        this.queue = queue;
        this.executor = executor;
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
            executor.execute(() -> consumer.accept(newMessage));
        }
    }
}
