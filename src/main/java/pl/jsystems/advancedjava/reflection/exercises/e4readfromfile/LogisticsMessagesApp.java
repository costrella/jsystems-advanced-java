package pl.jsystems.advancedjava.reflection.exercises.e4readfromfile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.reflection.exercises.e4readfromfile.contents.CargoLoadedMessageContent;
import pl.jsystems.advancedjava.reflection.exercises.e4readfromfile.contents.CargoUnloadedMessageContent;
import pl.jsystems.advancedjava.reflection.exercises.e4readfromfile.contents.GPSTrackingMessageContent;
import pl.jsystems.advancedjava.reflection.exercises.e4readfromfile.contents.MessageContent;
import pl.jsystems.advancedjava.reflection.exercises.e4readfromfile.message.Message;
import pl.jsystems.advancedjava.reflection.exercises.e4readfromfile.receivers.CargoLoadedMessageReceiver;
import pl.jsystems.advancedjava.reflection.exercises.e4readfromfile.receivers.CargoUnloadedMessageReceiver;
import pl.jsystems.advancedjava.reflection.exercises.e4readfromfile.receivers.GPSTrackingMessageReceiver;
import pl.jsystems.advancedjava.reflection.exercises.e4readfromfile.repositories.CargoLoadedMessageRepository;
import pl.jsystems.advancedjava.reflection.exercises.e4readfromfile.repositories.CargoUnloadedMessageRepository;
import pl.jsystems.advancedjava.reflection.exercises.e4readfromfile.repositories.GPSTrackingMessageRepository;

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

@MainClass
class LogisticsMessagesApp
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionExercise4ReadFromFile.class);

    private final CargoLoadedMessageReceiver cargoLoadedMessageReceiver;
    private final CargoUnloadedMessageReceiver cargoUnloadedMessageReceiver;
    private final GPSTrackingMessageReceiver gpsMessageReceiver;
    private final CargoLoadedMessageRepository cargoLoadedMessageRepository;
    private final CargoUnloadedMessageRepository cargoUnloadedMessageRepository;
    private final GPSTrackingMessageRepository gpsMessageRepository;
    private final MessageLogger messageLogger;

    LogisticsMessagesApp(CargoLoadedMessageReceiver cargoLoadedMessageReceiver,
                         CargoUnloadedMessageReceiver cargoUnloadedMessageReceiver,
                         GPSTrackingMessageReceiver gpsMessageReceiver,
                         CargoLoadedMessageRepository cargoLoadedMessageRepository,
                         CargoUnloadedMessageRepository cargoUnloadedMessageRepository,
                         GPSTrackingMessageRepository gpsMessageRepository,
                         MessageLogger messageLogger)
    {
        this.cargoLoadedMessageReceiver = cargoLoadedMessageReceiver;
        this.cargoUnloadedMessageReceiver = cargoUnloadedMessageReceiver;
        this.gpsMessageReceiver = gpsMessageReceiver;
        this.cargoLoadedMessageRepository = cargoLoadedMessageRepository;
        this.cargoUnloadedMessageRepository = cargoUnloadedMessageRepository;
        this.gpsMessageRepository = gpsMessageRepository;
        this.messageLogger = messageLogger;
    }

    void run()
    {
        LOGGER.info("Hello!");
        ExecutorService executors = Executors.newCachedThreadPool();
        Future<Integer> future = receiveAndStore(executors, messageLogger);

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() ->
        {
            List<Message<CargoLoadedMessageContent>> cargoLoadedMessages = cargoLoadedMessageRepository.findAll();
            List<Message<CargoUnloadedMessageContent>> cargoUnloadedMessages = cargoUnloadedMessageRepository.findAll();
            List<Message<GPSTrackingMessageContent>> gpsMessages = gpsMessageRepository.findAll();

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


    private Future<Integer> receiveAndStore(ExecutorService executors, MessageLogger messageLogger)
    {
        receiveAndStoreFor(cargoLoadedMessageReceiver,
                cargoLoadedMessageRepository,
                executors,
                messageLogger);

        receiveAndStoreFor(cargoUnloadedMessageReceiver,
                cargoUnloadedMessageRepository,
                executors,
                messageLogger);

        receiveAndStoreFor(gpsMessageReceiver,
                gpsMessageRepository,
                executors,
                messageLogger);

        return executors.submit(waitForAllToFinishRunnable());
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