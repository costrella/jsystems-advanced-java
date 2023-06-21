package pl.jsystems.advancedjava.streams.exercises.e9flatmap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.streams.exercises.e9flatmap.contents.CargoLoadedMessageContent;
import pl.jsystems.advancedjava.streams.exercises.e9flatmap.contents.CargoLoadingMessageContent;
import pl.jsystems.advancedjava.streams.exercises.e9flatmap.contents.CargoUnloadedMessageContent;
import pl.jsystems.advancedjava.streams.exercises.e9flatmap.message.Message;
import pl.jsystems.advancedjava.streams.exercises.e9flatmap.receivers.CargoUnloadedMessageReceiver;
import pl.jsystems.advancedjava.streams.exercises.e9flatmap.receivers.GPSTrackingMessageReceiver;
import pl.jsystems.advancedjava.streams.exercises.e9flatmap.receivers.CargoLoadedMessageReceiver;
import pl.jsystems.advancedjava.streams.exercises.e9flatmap.repositories.CargoLoadedMessageRepository;
import pl.jsystems.advancedjava.streams.exercises.e9flatmap.repositories.CargoUnloadedMessageRepository;
import pl.jsystems.advancedjava.streams.exercises.e9flatmap.repositories.GPSTrackingMessageRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Stream;

class StreamsExercise9FlatMap
{
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamsExercise9FlatMap.class);

    public static void main(String[] args)
    {
        new StreamsExercise9FlatMap().run();
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
        List<Message<CargoUnloadedMessageContent>> cargoUnloadedMessages = CARGO_UNLOADED_MESSAGE_REPOSITORY.findAll();
        LOGGER.info("All cargo loaded messages:");

        Stream<Message<? extends CargoLoadingMessageContent>> unloadedMessages = cargoUnloadedMessages.stream().map(msg -> msg);
        Stream<Message<? extends CargoLoadingMessageContent>> loadedMessages = cargoLoadedMessages.stream().map(msg -> msg);

        // todo: stuff...
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
