package pl.jsystems.advancedjava.threads.solutions.s6blockingqueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.threads.solutions.s6blockingqueue.contents.CargoLoadedMessageContent;
import pl.jsystems.advancedjava.threads.solutions.s6blockingqueue.message.Message;
import pl.jsystems.advancedjava.threads.solutions.s6blockingqueue.receivers.CargoLoadedMessageReceiver;
import pl.jsystems.advancedjava.threads.solutions.s6blockingqueue.repositories.CargoLoadedMessageRepository;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class ThreadsExercise6BlockingQueue
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsExercise6BlockingQueue.class);

    public static void main(String[] args)
    {
        new ThreadsExercise6BlockingQueue().run();
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


    private void receiveAndStore(MessageLogger messageLogger)
    {
        BlockingQueue<Message<CargoLoadedMessageContent>> messagesToStore = new ArrayBlockingQueue<>(5);
        new RepositoryStorageThread(messagesToStore, messageLogger).start();
        new RepositoryStorageThread(messagesToStore, messageLogger).start();
        new RepositoryStorageThread(messagesToStore, messageLogger).start();
        Thread thread = new CargoLoadedMessageReceiver()
                .startReceivingUsing(message ->
                {
                    try
                    {
                        messagesToStore.put(message);
                    } catch (InterruptedException e)
                    {
                        Thread.currentThread().interrupt();
                        LOGGER.error("Interrupted!", e);
                        throw new RuntimeException("Interrupted!", e);
                    }
                });


        joinThread(thread);
    }

    static class RepositoryStorageThread extends Thread
    {
        private final BlockingQueue<Message<CargoLoadedMessageContent>> messagesToStore;

        RepositoryStorageThread(BlockingQueue<Message<CargoLoadedMessageContent>> messagesToStore, MessageLogger messageLogger)
        {
            this.messagesToStore = messagesToStore;
            this.messageLogger = messageLogger;
        }

        private final MessageLogger messageLogger;

        @Override
        public void run()
        {
            {
                Message<CargoLoadedMessageContent> message;
                while (true)
                {
                    try
                    {
                        message = messagesToStore.take();
                    } catch (InterruptedException e)
                    {
                        Thread.currentThread().interrupt();
                        LOGGER.error("Interrupted!", e);
                        throw new RuntimeException("Interrupted!", e);
                    }
                    messageLogger.logReceived(message);
                    CARGO_LOADED_MESSAGE_REPOSITORY.save(message);
                    LOGGER.info("Saved Cargo Loaded message: {}", message);
                }
            }
        }
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
