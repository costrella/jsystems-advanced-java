package pl.jsystems.advancedjava.streams.exercises.e10collectingtomap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.streams.exercises.e10collectingtomap.contents.CargoLoadedMessageContent;
import pl.jsystems.advancedjava.streams.exercises.e10collectingtomap.message.Message;
import pl.jsystems.advancedjava.streams.exercises.e10collectingtomap.receivers.CargoLoadedMessageReceiver;
import pl.jsystems.advancedjava.streams.exercises.e10collectingtomap.receivers.CargoUnloadedMessageReceiver;
import pl.jsystems.advancedjava.streams.exercises.e10collectingtomap.receivers.GPSTrackingMessageReceiver;
import pl.jsystems.advancedjava.streams.exercises.e10collectingtomap.repositories.CargoLoadedMessageRepository;
import pl.jsystems.advancedjava.streams.exercises.e10collectingtomap.repositories.CargoUnloadedMessageRepository;
import pl.jsystems.advancedjava.streams.exercises.e10collectingtomap.repositories.GPSTrackingMessageRepository;

import java.util.List;

class StreamsExercise10CollectingToMap
{
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamsExercise10CollectingToMap.class);

    public static void main(String[] args)
    {
        new StreamsExercise10CollectingToMap().run();
    }

    private static final GPSTrackingMessageRepository GPS_TRACKING_MESSAGE_REPOSITORY = new GPSTrackingMessageRepository();
    private static final CargoLoadedMessageRepository CARGO_LOADED_MESSAGE_REPOSITORY = new CargoLoadedMessageRepository();
    private static final CargoUnloadedMessageRepository CARGO_UNLOADED_MESSAGE_REPOSITORY = new CargoUnloadedMessageRepository();

    private void run()
    {
        LOGGER.info("Time to consume some messages!");
        MessageLogger messageLogger = new MessageLogger();
        receiveAndStore(messageLogger);

        List<Message<CargoLoadedMessageContent>> cargoLoadedMessages = CARGO_LOADED_MESSAGE_REPOSITORY.findAll();
        LOGGER.info("All cargo loaded messages:");

        // todo stuff
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