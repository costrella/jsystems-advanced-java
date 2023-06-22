package pl.jsystems.advancedjava.streams.solutions.s10collectingtomap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.streams.solutions.s10collectingtomap.contents.CargoLoadedMessageContent;
import pl.jsystems.advancedjava.streams.solutions.s10collectingtomap.message.Message;
import pl.jsystems.advancedjava.streams.solutions.s10collectingtomap.receivers.CargoLoadedMessageReceiver;
import pl.jsystems.advancedjava.streams.solutions.s10collectingtomap.receivers.CargoUnloadedMessageReceiver;
import pl.jsystems.advancedjava.streams.solutions.s10collectingtomap.receivers.GPSTrackingMessageReceiver;
import pl.jsystems.advancedjava.streams.solutions.s10collectingtomap.repositories.CargoLoadedMessageRepository;
import pl.jsystems.advancedjava.streams.solutions.s10collectingtomap.repositories.CargoUnloadedMessageRepository;
import pl.jsystems.advancedjava.streams.solutions.s10collectingtomap.repositories.GPSTrackingMessageRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

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

        var randomCargoWeightById = cargoLoadedMessages.stream()
                .distinct()
                .collect(Collectors.toMap(Message::id, message -> message.content().getCargoLoadInKgs()));
        LOGGER.info("Unique random messages with weight: {}", randomCargoWeightById);

        Map<UUID, BigDecimal> cargoWeightById = cargoLoadedMessages.stream()
                .collect(Collectors.toMap(Message::id, message -> message.content().getCargoLoadInKgs(), BigDecimal::add));
        LOGGER.info("Sum of weights by message id: {}", cargoWeightById);
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
