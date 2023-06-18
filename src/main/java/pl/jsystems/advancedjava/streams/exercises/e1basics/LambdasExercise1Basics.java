package pl.jsystems.advancedjava.streams.exercises.e1basics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.streams.exercises.e1basics.contents.CargoLoadedMessageContent;
import pl.jsystems.advancedjava.streams.exercises.e1basics.message.Message;
import pl.jsystems.advancedjava.streams.exercises.e1basics.receivers.CargoLoadedMessageReceiver;
import pl.jsystems.advancedjava.streams.exercises.e1basics.receivers.CargoUnloadedMessageReceiver;
import pl.jsystems.advancedjava.streams.exercises.e1basics.receivers.GPSTrackingMessageReceiver;
import pl.jsystems.advancedjava.streams.exercises.e1basics.repositories.CargoLoadedMessageRepository;
import pl.jsystems.advancedjava.streams.exercises.e1basics.repositories.CargoUnloadedMessageRepository;
import pl.jsystems.advancedjava.streams.exercises.e1basics.repositories.GPSTrackingMessageRepository;

import java.util.List;

class LambdasExercise1Basics
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LambdasExercise1Basics.class);

    public static void main(String[] args)
    {
        new LambdasExercise1Basics().run();
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
        LOGGER.info("All cargo loaded messages: {}", cargoLoadedMessages);

        // TODO stuff...
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
