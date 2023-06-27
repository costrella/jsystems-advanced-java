package pl.jsystems.advancedjava.reflection.exercises.e2breakingstuff;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.reflection.exercises.e2breakingstuff.contents.CargoLoadedMessageContent;
import pl.jsystems.advancedjava.reflection.exercises.e2breakingstuff.contents.CargoUnloadedMessageContent;
import pl.jsystems.advancedjava.reflection.exercises.e2breakingstuff.contents.GPSTrackingMessageContent;
import pl.jsystems.advancedjava.reflection.exercises.e2breakingstuff.contents.MessageContent;
import pl.jsystems.advancedjava.reflection.exercises.e2breakingstuff.message.Message;
import pl.jsystems.advancedjava.reflection.exercises.e2breakingstuff.receivers.CargoLoadedMessageReceiver;
import pl.jsystems.advancedjava.reflection.exercises.e2breakingstuff.receivers.CargoUnloadedMessageReceiver;
import pl.jsystems.advancedjava.reflection.exercises.e2breakingstuff.receivers.GPSTrackingMessageReceiver;
import pl.jsystems.advancedjava.reflection.exercises.e2breakingstuff.repositories.CargoLoadedMessageRepository;
import pl.jsystems.advancedjava.reflection.exercises.e2breakingstuff.repositories.CargoUnloadedMessageRepository;
import pl.jsystems.advancedjava.reflection.exercises.e2breakingstuff.repositories.GPSTrackingMessageRepository;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

class LogisticsMessagesApp
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionExercise2BreakingStuff.class);
    private static final CargoLoadedMessageReceiver CARGO_LOADED_MESSAGE_RECEIVER = new CargoLoadedMessageReceiver();
    private static final CargoUnloadedMessageReceiver CARGO_UNLOADED_MESSAGE_RECEIVER = new CargoUnloadedMessageReceiver();
    private static final GPSTrackingMessageReceiver GPS_MESSAGE_RECEIVER = new GPSTrackingMessageReceiver();

    private static final CargoLoadedMessageRepository CARGO_LOADED_MESSAGE_REPOSITORY = new CargoLoadedMessageRepository();

    private static final CargoUnloadedMessageRepository CARGO_UNLOADED_MESSAGE_REPOSITORY = new CargoUnloadedMessageRepository();
    private static final GPSTrackingMessageRepository GPS_MESSAGE_REPOSITORY = new GPSTrackingMessageRepository();

    private final MessageLogger messageLogger = new MessageLogger();

    void run(String welcomePrompt)
    {
        LOGGER.info(welcomePrompt);
        ExecutorService executors = Executors.newCachedThreadPool();
        Future<Integer> future = receiveAndStore(executors, messageLogger);

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() ->
        {
            List<Message<CargoLoadedMessageContent>> cargoLoadedMessages = CARGO_LOADED_MESSAGE_REPOSITORY.findAll();
            List<Message<CargoUnloadedMessageContent>> cargoUnloadedMessages = CARGO_UNLOADED_MESSAGE_REPOSITORY.findAll();
            List<Message<GPSTrackingMessageContent>> gpsMessages = GPS_MESSAGE_REPOSITORY.findAll();

            LOGGER.info("Cargo Loaded messages size {}", cargoLoadedMessages.size());
            LOGGER.info("Cargo Unloaded messages size {}", cargoUnloadedMessages.size());
            LOGGER.info("GPS messages size {}", gpsMessages.size());
        }, 10, 10, TimeUnit.SECONDS);

        LOGGER.info("Waiting till end");
        Integer integer = waitForFuture(future);
        LOGGER.info("Time to wrap up after {}ms.", integer);
        executors.shutdown();
        scheduler.shutdown();

        LOGGER.info("Waiting for all threads to stop.");
        waitForGracefulShutdown();
        executors.shutdownNow();
        scheduler.shutdownNow();
    }

    private static void waitForGracefulShutdown()
    {
        try
        {
            Thread.sleep(15000);
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            LOGGER.error("Interrupted!", e);
            throw new RuntimeException("Interrupted!", e);
        }
    }

    private Integer waitForFuture(final Future<Integer> future)
    {
        try
        {
            return future.get();
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            LOGGER.error("Interrupted!", e);
            throw new RuntimeException("Interrupted!", e);
        } catch (ExecutionException e)
        {
            LOGGER.error("Error while executing task: {}", e.getMessage());
            throw new RuntimeException("Error while executing task", e);
        }
    }


    private Future<Integer> receiveAndStore(ExecutorService executors, MessageLogger messageLogger)
    {
        receiveAndStoreFor(CARGO_LOADED_MESSAGE_RECEIVER,
                CARGO_LOADED_MESSAGE_REPOSITORY,
                executors,
                messageLogger);

        receiveAndStoreFor(CARGO_UNLOADED_MESSAGE_RECEIVER,
                CARGO_UNLOADED_MESSAGE_REPOSITORY,
                executors,
                messageLogger);

        receiveAndStoreFor(GPS_MESSAGE_RECEIVER,
                GPS_MESSAGE_REPOSITORY,
                executors,
                messageLogger);

        return executors.submit(waitForAllToFinishRunnable());
    }

    private Callable<Integer> waitForAllToFinishRunnable()
    {
        return () ->
        {
            try
            {
                int timeout = 12000;
                Thread.sleep(timeout);
                return timeout;
            } catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
                LOGGER.error("Interrupted!");
                throw new RuntimeException("Interrupted!", e);
            }
        };
    }

    private <T extends MessageContent> void receiveAndStoreFor(MessageReceiver<T> receiver,
                                                               MessageRepository<T> repository,
                                                               Executor executor,
                                                               MessageLogger messageLogger)
    {
        BlockingQueue<Message<T>> cargoUnloadedMessageQueue = new ArrayBlockingQueue<>(20);
        Consumer<Message<T>> cargoUnloadedMessageConsumer = createRepositoryConsumer(repository, messageLogger);
        receiver.startReceivingUsing(executor, createReceiverConsumer(cargoUnloadedMessageQueue));

        Runnable runnable = new MessageRepositoryWorkerThread<>(cargoUnloadedMessageQueue, executor, cargoUnloadedMessageConsumer);
        executor.execute(runnable);
    }

    private <T extends MessageContent> Consumer<Message<T>> createReceiverConsumer(BlockingQueue<Message<T>> messageQueue)
    {
        return message ->
        {
            try
            {
                messageQueue.put(message);
            } catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
                LOGGER.info("Interrupted!", e);
                throw new RuntimeException("Interrupted!", e);
            }
        };
    }

    private <T extends MessageContent> Consumer<Message<T>> createRepositoryConsumer(MessageRepository<T> repository,
                                                                                     MessageLogger messageLogger)
    {
        return message ->
        {
            messageLogger.logReceived(message);
            repository.save(message);
            LOGGER.info("Saved {} message: {}", message.content().getClass().getSimpleName(), message.id());
        };
    }
}
