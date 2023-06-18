package pl.jsystems.advancedjava.streams.exercises.e3map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.streams.exercises.e3map.contents.CargoLoadedMessageContent;
import pl.jsystems.advancedjava.streams.exercises.e3map.message.Message;
import pl.jsystems.advancedjava.streams.exercises.e3map.receivers.CargoLoadedMessageReceiver;
import pl.jsystems.advancedjava.streams.exercises.e3map.receivers.CargoUnloadedMessageReceiver;
import pl.jsystems.advancedjava.streams.exercises.e3map.receivers.GPSTrackingMessageReceiver;
import pl.jsystems.advancedjava.streams.exercises.e3map.repositories.CargoLoadedMessageRepository;
import pl.jsystems.advancedjava.streams.exercises.e3map.repositories.CargoUnloadedMessageRepository;
import pl.jsystems.advancedjava.streams.exercises.e3map.repositories.GPSTrackingMessageRepository;

import java.util.List;

class LambdasExercise3Map
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LambdasExercise3Map.class);

    public static void main(String[] args)
    {
        new LambdasExercise3Map().run();
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
        cargoLoadedMessages.forEach(messageLogger::logReceived);

        // TODO: stuff...
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
