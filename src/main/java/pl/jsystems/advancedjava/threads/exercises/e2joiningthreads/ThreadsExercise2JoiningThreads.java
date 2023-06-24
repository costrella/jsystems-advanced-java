package pl.jsystems.advancedjava.threads.exercises.e2joiningthreads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.threads.exercises.e2joiningthreads.contents.CargoLoadedMessageContent;
import pl.jsystems.advancedjava.threads.exercises.e2joiningthreads.message.Message;
import pl.jsystems.advancedjava.threads.exercises.e2joiningthreads.receivers.CargoLoadedMessageReceiver;
import pl.jsystems.advancedjava.threads.exercises.e2joiningthreads.receivers.CargoUnloadedMessageReceiver;
import pl.jsystems.advancedjava.threads.exercises.e2joiningthreads.receivers.GPSTrackingMessageReceiver;
import pl.jsystems.advancedjava.threads.exercises.e2joiningthreads.repositories.CargoLoadedMessageRepository;
import pl.jsystems.advancedjava.threads.exercises.e2joiningthreads.repositories.CargoUnloadedMessageRepository;
import pl.jsystems.advancedjava.threads.exercises.e2joiningthreads.repositories.GPSTrackingMessageRepository;

import java.time.Instant;
import java.util.List;

class ThreadsExercise2JoiningThreads
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsExercise2JoiningThreads.class);

    public static void main(String[] args)
    {
        new ThreadsExercise2JoiningThreads().run();
    }

    private static final GPSTrackingMessageRepository GPS_TRACKING_MESSAGE_REPOSITORY = new GPSTrackingMessageRepository();
    private static final CargoLoadedMessageRepository CARGO_LOADED_MESSAGE_REPOSITORY = new CargoLoadedMessageRepository();
    private static final CargoUnloadedMessageRepository CARGO_UNLOADED_MESSAGE_REPOSITORY = new CargoUnloadedMessageRepository();

    private void run()
    {
        int startTime = Instant.now().getNano();
        LOGGER.info("Multithreading...");
        MessageLogger messageLogger = new MessageLogger();
        receiveAndStore(messageLogger);

        List<Message<CargoLoadedMessageContent>> cargoLoadedMessages = CARGO_LOADED_MESSAGE_REPOSITORY.findAll();

        // those logs are not ok - we'll fix that soon!
        LOGGER.info("All messages - size {}: {}", cargoLoadedMessages.size(), cargoLoadedMessages);
        int endTime = Instant.now().getNano();
        LOGGER.info("Time spent processing (ms): {}", (endTime - startTime) / 1000000);
    }


    private static void receiveAndStore(MessageLogger messageLogger)
    {
        new GPSTrackingMessageReceiver().startReceivingUsing(message ->
        {
            messageLogger.logReceived(message);
            GPS_TRACKING_MESSAGE_REPOSITORY.save(message);
            LOGGER.info("Saved GPS message: {}", message);
        });

        new CargoLoadedMessageReceiver().startReceivingUsing(message ->
        {
            messageLogger.logReceived(message);
            CARGO_LOADED_MESSAGE_REPOSITORY.save(message);
            LOGGER.info("Saved GPS message: {}", message);
        });

        new CargoUnloadedMessageReceiver().startReceivingUsing(message ->
        {
            messageLogger.logReceived(message);
            CARGO_UNLOADED_MESSAGE_REPOSITORY.save(message);
            LOGGER.info("Saved GPS message: {}", message);
        });
    }
}
