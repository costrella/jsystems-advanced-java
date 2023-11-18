package pl.jsystems.advancedjava.threads.exercises.e13future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.threads.exercises.e13future.contents.CargoLoadedMessageContent;
import pl.jsystems.advancedjava.threads.exercises.e13future.contents.CargoUnloadedMessageContent;
import pl.jsystems.advancedjava.threads.exercises.e13future.contents.GPSTrackingMessageContent;
import pl.jsystems.advancedjava.threads.exercises.e13future.contents.MessageContent;
import pl.jsystems.advancedjava.threads.exercises.e13future.message.Message;
import pl.jsystems.advancedjava.threads.exercises.e13future.receivers.CargoLoadedMessageReceiver;
import pl.jsystems.advancedjava.threads.exercises.e13future.receivers.CargoUnloadedMessageReceiver;
import pl.jsystems.advancedjava.threads.exercises.e13future.receivers.GPSTrackingMessageReceiver;
import pl.jsystems.advancedjava.threads.exercises.e13future.repositories.CargoLoadedMessageRepository;
import pl.jsystems.advancedjava.threads.exercises.e13future.repositories.CargoUnloadedMessageRepository;
import pl.jsystems.advancedjava.threads.exercises.e13future.repositories.GPSTrackingMessageRepository;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

class ThreadsExercise13Future
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsExercise13Future.class);
    public static final CargoLoadedMessageReceiver CARGO_LOADED_MESSAGE_RECEIVER = new CargoLoadedMessageReceiver();
    public static final CargoUnloadedMessageReceiver CARGO_UNLOADED_MESSAGE_RECEIVER = new CargoUnloadedMessageReceiver();
    public static final GPSTrackingMessageReceiver GPS_MESSAGE_RECEIVER = new GPSTrackingMessageReceiver();

    private static final CargoLoadedMessageRepository CARGO_LOADED_MESSAGE_REPOSITORY = new CargoLoadedMessageRepository();

    private static final CargoUnloadedMessageRepository CARGO_UNLOADED_MESSAGE_REPOSITORY = new CargoUnloadedMessageRepository();
    private static final GPSTrackingMessageRepository GPS_MESSAGE_REPOSITORY = new GPSTrackingMessageRepository();

    public static void main(String[] args)
    {
        new ThreadsExercise13Future().run();
    }

    private void run()
    {
        long startTime = Instant.now().toEpochMilli();
        LOGGER.info("Multithreading...");
        MessageLogger messageLogger = new MessageLogger();
        receiveAndStore(messageLogger);

        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() ->
        {
            List<Message<CargoLoadedMessageContent>> cargoLoadedMessages = CARGO_LOADED_MESSAGE_REPOSITORY.findAll();
            List<Message<CargoUnloadedMessageContent>> cargoUnloadedMessages = CARGO_UNLOADED_MESSAGE_REPOSITORY.findAll();
            List<Message<GPSTrackingMessageContent>> gpsMessages = GPS_MESSAGE_REPOSITORY.findAll();

            LOGGER.info("Cargo Loaded messages size {}", cargoLoadedMessages.size());
            LOGGER.info("Cargo Unloaded messages size {}", cargoUnloadedMessages.size());
            LOGGER.info("GPS messages size {}", gpsMessages.size());
        }, 10, 10, TimeUnit.SECONDS);
    }


    private void receiveAndStore(MessageLogger messageLogger)
    {
        ExecutorService executors = Executors.newCachedThreadPool();

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

        waitForAllToFinish();

        executors.shutdown();
    }

    private static void waitForAllToFinish()
    {
        try
        {
            Thread.sleep(120000);
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            LOGGER.error("Interrupted!");
            throw new RuntimeException("Interrupted!", e);
        }
    }

    private <T extends MessageContent> void receiveAndStoreFor(MessageReceiver<T> receiver,
                                                               MessageRepository<T> repository,
                                                               Executor executor,
                                                               MessageLogger messageLogger)
    {
        BlockingQueue<Message<T>> cargoUnloadedMessageQueue = new ArrayBlockingQueue<>(20);
        Consumer<Message<T>> cargoUnloadedMessageConsumer = createRepositoryConsumer(repository, messageLogger);
        receiver.startReceivingUsing(createReceiverConsumer(cargoUnloadedMessageQueue));

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