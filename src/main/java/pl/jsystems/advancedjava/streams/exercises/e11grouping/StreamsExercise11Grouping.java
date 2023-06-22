package pl.jsystems.advancedjava.streams.exercises.e11grouping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.streams.exercises.e11grouping.contents.CargoUnloadedMessageContent;
import pl.jsystems.advancedjava.streams.exercises.e11grouping.repositories.CargoUnloadedMessageRepository;
import pl.jsystems.advancedjava.streams.exercises.e11grouping.contents.CargoLoadedMessageContent;
import pl.jsystems.advancedjava.streams.exercises.e11grouping.message.Message;
import pl.jsystems.advancedjava.streams.exercises.e11grouping.receivers.CargoLoadedMessageReceiver;
import pl.jsystems.advancedjava.streams.exercises.e11grouping.receivers.CargoUnloadedMessageReceiver;
import pl.jsystems.advancedjava.streams.exercises.e11grouping.receivers.GPSTrackingMessageReceiver;
import pl.jsystems.advancedjava.streams.exercises.e11grouping.repositories.CargoLoadedMessageRepository;
import pl.jsystems.advancedjava.streams.exercises.e11grouping.repositories.GPSTrackingMessageRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

class StreamsExercise11Grouping
{
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamsExercise11Grouping.class);

    public static void main(String[] args)
    {
        new StreamsExercise11Grouping().run();
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

        LOGGER.info("Let's practice grouping stuff!");
        // TODO
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
