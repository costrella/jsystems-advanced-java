package pl.jsystems.advancedjava.streams.exercises.e12partitioning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.streams.exercises.e12partitioning.contents.CargoLoadedMessageContent;
import pl.jsystems.advancedjava.streams.exercises.e12partitioning.contents.CargoUnloadedMessageContent;
import pl.jsystems.advancedjava.streams.exercises.e12partitioning.message.Message;
import pl.jsystems.advancedjava.streams.exercises.e12partitioning.receivers.CargoLoadedMessageReceiver;
import pl.jsystems.advancedjava.streams.exercises.e12partitioning.receivers.CargoUnloadedMessageReceiver;
import pl.jsystems.advancedjava.streams.exercises.e12partitioning.receivers.GPSTrackingMessageReceiver;
import pl.jsystems.advancedjava.streams.exercises.e12partitioning.repositories.CargoLoadedMessageRepository;
import pl.jsystems.advancedjava.streams.exercises.e12partitioning.repositories.CargoUnloadedMessageRepository;
import pl.jsystems.advancedjava.streams.exercises.e12partitioning.repositories.GPSTrackingMessageRepository;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

class StreamsExercise12Partitioning
{
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamsExercise12Partitioning.class);

    public static void main(String[] args)
    {
        new StreamsExercise12Partitioning().run();
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

        LOGGER.info("True or false? You tell me!");

        // todo - stuff...
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
