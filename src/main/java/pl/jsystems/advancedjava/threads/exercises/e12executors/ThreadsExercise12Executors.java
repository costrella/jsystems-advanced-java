package pl.jsystems.advancedjava.threads.exercises.e12executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.threads.exercises.e12executors.contents.CargoLoadedMessageContent;
import pl.jsystems.advancedjava.threads.exercises.e12executors.contents.CargoUnloadedMessageContent;
import pl.jsystems.advancedjava.threads.exercises.e12executors.contents.GPSTrackingMessageContent;
import pl.jsystems.advancedjava.threads.exercises.e12executors.contents.MessageContent;
import pl.jsystems.advancedjava.threads.exercises.e12executors.message.Message;
import pl.jsystems.advancedjava.threads.exercises.e12executors.receivers.CargoLoadedMessageReceiver;
import pl.jsystems.advancedjava.threads.exercises.e12executors.receivers.CargoUnloadedMessageReceiver;
import pl.jsystems.advancedjava.threads.exercises.e12executors.receivers.GPSTrackingMessageReceiver;
import pl.jsystems.advancedjava.threads.exercises.e12executors.repositories.CargoLoadedMessageRepository;
import pl.jsystems.advancedjava.threads.exercises.e12executors.repositories.CargoUnloadedMessageRepository;
import pl.jsystems.advancedjava.threads.exercises.e12executors.repositories.GPSTrackingMessageRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;

class ThreadsExercise12Executors
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsExercise12Executors.class);
    public static final CargoLoadedMessageReceiver CARGO_LOADED_MESSAGE_RECEIVER = new CargoLoadedMessageReceiver();
    public static final CargoUnloadedMessageReceiver CARGO_UNLOADED_MESSAGE_RECEIVER = new CargoUnloadedMessageReceiver();
    public static final GPSTrackingMessageReceiver GPS_MESSAGE_RECEIVER = new GPSTrackingMessageReceiver();

    private static final CargoLoadedMessageRepository CARGO_LOADED_MESSAGE_REPOSITORY = new CargoLoadedMessageRepository();

    private static final CargoUnloadedMessageRepository CARGO_UNLOADED_MESSAGE_REPOSITORY = new CargoUnloadedMessageRepository();
    private static final GPSTrackingMessageRepository GPS_MESSAGE_REPOSITORY = new GPSTrackingMessageRepository();

    public static void main(String[] args)
    {
        new ThreadsExercise12Executors().run();
    }

    private void run()
    {
        LOGGER.info("Multithreading...");
        MessageLogger messageLogger = new MessageLogger();
        receiveAndStore(messageLogger);

        List<Message<CargoLoadedMessageContent>> cargoLoadedMessages = CARGO_LOADED_MESSAGE_REPOSITORY.findAll();
        List<Message<CargoUnloadedMessageContent>> cargoUnloadedMessages = CARGO_UNLOADED_MESSAGE_REPOSITORY.findAll();
        List<Message<GPSTrackingMessageContent>> gpsMessages = GPS_MESSAGE_REPOSITORY.findAll();

        LOGGER.info("Cargo Loaded messages size {}", cargoLoadedMessages.size());
        LOGGER.info("Cargo Unloaded messages size {}", cargoUnloadedMessages.size());
        LOGGER.info("GPS messages size {}", gpsMessages.size());
    }


    private void receiveAndStore(MessageLogger messageLogger)
    {
        List<Thread> cargoLoadedRepositoryWorkerThreads = receiveAndStoreFor(CARGO_LOADED_MESSAGE_RECEIVER,
                CARGO_LOADED_MESSAGE_REPOSITORY,
                10,
                messageLogger);

        List<Thread> cargoUnloadedRepositoryWorkerThreads = receiveAndStoreFor(CARGO_UNLOADED_MESSAGE_RECEIVER,
                CARGO_UNLOADED_MESSAGE_REPOSITORY,
                10,
                messageLogger);

        List<Thread> gpsRepositoryWorkerThreads = receiveAndStoreFor(GPS_MESSAGE_RECEIVER,
                GPS_MESSAGE_REPOSITORY,
                10,
                messageLogger);

        waitForAllToFinish();

        // uncomment this if you'd like for app to close on its own.
        /*
        Stream.of(cargoLoadedRepositoryWorkerThreads,
                        cargoUnloadedRepositoryWorkerThreads,
                        gpsRepositoryWorkerThreads
                )
                .flatMap(List::stream)
                .forEach(Thread::interrupt);
        */
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

    private <T extends MessageContent> List<Thread> receiveAndStoreFor(MessageReceiver<T> receiver,
                                                                       MessageRepository<T> repository,
                                                                       int threadCount,
                                                                       MessageLogger messageLogger)
    {
        BlockingQueue<Message<T>> cargoUnloadedMessageQueue = new ArrayBlockingQueue<>(20);
        Consumer<Message<T>> cargoUnloadedMessageConsumer = createRepositoryConsumer(repository, messageLogger);
        receiver.startReceivingUsing(createReceiverConsumer(cargoUnloadedMessageQueue));

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < threadCount; i++)
        {
            Thread repositoryWorkerThread = new MessageRepositoryWorkerThread<>(cargoUnloadedMessageQueue, cargoUnloadedMessageConsumer);
            repositoryWorkerThread.start();
            threads.add(repositoryWorkerThread);
        }

        return threads;
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

