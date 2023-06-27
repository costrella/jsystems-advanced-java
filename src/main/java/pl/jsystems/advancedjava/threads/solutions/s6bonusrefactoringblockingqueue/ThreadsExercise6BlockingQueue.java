package pl.jsystems.advancedjava.threads.solutions.s6bonusrefactoringblockingqueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.threads.solutions.s6bonusrefactoringblockingqueue.contents.CargoLoadedMessageContent;
import pl.jsystems.advancedjava.threads.solutions.s6bonusrefactoringblockingqueue.contents.CargoUnloadedMessageContent;
import pl.jsystems.advancedjava.threads.solutions.s6bonusrefactoringblockingqueue.contents.GPSTrackingMessageContent;
import pl.jsystems.advancedjava.threads.solutions.s6bonusrefactoringblockingqueue.message.Message;
import pl.jsystems.advancedjava.threads.solutions.s6bonusrefactoringblockingqueue.receivers.CargoLoadedMessageReceiver;
import pl.jsystems.advancedjava.threads.solutions.s6bonusrefactoringblockingqueue.receivers.CargoUnloadedMessageReceiver;
import pl.jsystems.advancedjava.threads.solutions.s6bonusrefactoringblockingqueue.receivers.GPSTrackingMessageReceiver;
import pl.jsystems.advancedjava.threads.solutions.s6bonusrefactoringblockingqueue.repositories.CargoLoadedMessageRepository;
import pl.jsystems.advancedjava.threads.solutions.s6bonusrefactoringblockingqueue.repositories.CargoUnloadedMessageRepository;
import pl.jsystems.advancedjava.threads.solutions.s6bonusrefactoringblockingqueue.repositories.GPSTrackingMessageRepository;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;

class ThreadsExercise6BlockingQueue
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsExercise6BlockingQueue.class);

    private static final CargoLoadedMessageRepository CARGO_LOADED_MESSAGE_REPOSITORY = new CargoLoadedMessageRepository();
    private static final CargoUnloadedMessageRepository CARGO_UNLOADED_MESSAGE_REPOSITORY = new CargoUnloadedMessageRepository();
    private static final GPSTrackingMessageRepository GPS_MESSAGE_REPOSITORY = new GPSTrackingMessageRepository();

    public static void main(String[] args)
    {
        new ThreadsExercise6BlockingQueue().run();
    }

    private void run()
    {
        long startTime = Instant.now().toEpochMilli();
        LOGGER.info("Multithreading...");
        MessageLogger messageLogger = new MessageLogger();
        receiveAndStore(messageLogger);

        List<Message<CargoLoadedMessageContent>> cargoLoadedMessages = CARGO_LOADED_MESSAGE_REPOSITORY.findAll();
        List<Message<CargoUnloadedMessageContent>> cargoUnloadedMessages = CARGO_UNLOADED_MESSAGE_REPOSITORY.findAll();
        List<Message<GPSTrackingMessageContent>> gpsMessages = GPS_MESSAGE_REPOSITORY.findAll();

        LOGGER.info("Cargo Loaded messages size {}", cargoLoadedMessages.size());
        LOGGER.info("Cargo Unloaded messages size {}", cargoUnloadedMessages.size());
        LOGGER.info("GPS messages size {}", gpsMessages.size());
        long endTime = Instant.now().toEpochMilli();
        LOGGER.info("Time spent processing (ms): {}", (endTime - startTime));
    }


    private static void receiveAndStore(MessageLogger messageLogger)
    {
        BlockingQueue<Message<CargoLoadedMessageContent>> cargoLoadedMessageQueue = new ArrayBlockingQueue<>(20);
        Consumer<Message<CargoLoadedMessageContent>> cargoLoadedMessageConsumer = message ->
        {
            messageLogger.logReceived(message);
            CARGO_LOADED_MESSAGE_REPOSITORY.save(message);
            LOGGER.info("Saved Cargo Loaded message: {}", message);
        };
        Thread cargoLoadedRepositoryWorkerThread1 = new MessageRepositoryWorkerThread<>(cargoLoadedMessageQueue, cargoLoadedMessageConsumer);
        Thread cargoLoadedRepositoryWorkerThread2 = new MessageRepositoryWorkerThread<>(cargoLoadedMessageQueue, cargoLoadedMessageConsumer);
        Thread cargoLoadedRepositoryWorkerThread3 = new MessageRepositoryWorkerThread<>(cargoLoadedMessageQueue, cargoLoadedMessageConsumer);
        cargoLoadedRepositoryWorkerThread1.start();
        cargoLoadedRepositoryWorkerThread2.start();
        cargoLoadedRepositoryWorkerThread3.start();

        BlockingQueue<Message<CargoUnloadedMessageContent>> cargoUnloadedMessageQueue = new ArrayBlockingQueue<>(20);
        Consumer<Message<CargoUnloadedMessageContent>> cargoUnloadedMessageConsumer = message ->
        {
            messageLogger.logReceived(message);
            CARGO_UNLOADED_MESSAGE_REPOSITORY.save(message);
            LOGGER.info("Saved Cargo Loaded message: {}", message);
        };
        Thread cargoUnloadedRepositoryWorkerThread = new MessageRepositoryWorkerThread<>(cargoUnloadedMessageQueue, cargoUnloadedMessageConsumer);
        cargoUnloadedRepositoryWorkerThread.start();

        BlockingQueue<Message<GPSTrackingMessageContent>> gpsMessageQueue = new ArrayBlockingQueue<>(20);
        Consumer<Message<GPSTrackingMessageContent>> gpsMessageConsumer = message ->
        {
            messageLogger.logReceived(message);
            GPS_MESSAGE_REPOSITORY.save(message);
            LOGGER.info("Saved Cargo Loaded message: {}", message);
        };
        Thread gpsRepositoryWorkerThread = new MessageRepositoryWorkerThread<>(gpsMessageQueue, gpsMessageConsumer);
        gpsRepositoryWorkerThread.start();
        new CargoLoadedMessageReceiver().startReceivingUsing(message ->
        {
            try
            {
                cargoLoadedMessageQueue.put(message);
            } catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
                LOGGER.info("Interrupted!", e);
                throw new RuntimeException("Interrupted!", e);
            }
        });
        new CargoUnloadedMessageReceiver().startReceivingUsing(message ->
        {
            try
            {
                cargoUnloadedMessageQueue.put(message);
            } catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
                LOGGER.info("Interrupted!", e);
                throw new RuntimeException("Interrupted!", e);
            }
        });
        new GPSTrackingMessageReceiver().startReceivingUsing(message ->
        {
            try
            {
                gpsMessageQueue.put(message);
            } catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
                LOGGER.info("Interrupted!", e);
                throw new RuntimeException("Interrupted!", e);
            }
        });

        try
        {
            Thread.sleep(10000);
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            LOGGER.error("Interrupted!");
            throw new RuntimeException("Interrupted!", e);
        }

        cargoLoadedRepositoryWorkerThread1.interrupt();
        cargoLoadedRepositoryWorkerThread2.interrupt();
        cargoLoadedRepositoryWorkerThread3.interrupt();
        cargoUnloadedRepositoryWorkerThread.interrupt();
        gpsRepositoryWorkerThread.interrupt();
    }
}
