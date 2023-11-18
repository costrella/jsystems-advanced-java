package pl.jsystems.advancedjava.streams.solutions.s5distinct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.streams.solutions.s5distinct.contents.CargoLoadedMessageContent;
import pl.jsystems.advancedjava.streams.solutions.s5distinct.message.Message;
import pl.jsystems.advancedjava.streams.solutions.s5distinct.receivers.CargoLoadedMessageReceiver;
import pl.jsystems.advancedjava.streams.solutions.s5distinct.receivers.CargoUnloadedMessageReceiver;
import pl.jsystems.advancedjava.streams.solutions.s5distinct.receivers.GPSTrackingMessageReceiver;
import pl.jsystems.advancedjava.streams.solutions.s5distinct.repositories.CargoLoadedMessageRepository;
import pl.jsystems.advancedjava.streams.solutions.s5distinct.repositories.CargoUnloadedMessageRepository;
import pl.jsystems.advancedjava.streams.solutions.s5distinct.repositories.GPSTrackingMessageRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

class StreamsExercise5Distinct
{
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamsExercise5Distinct.class);

    public static void main(String[] args)
    {
        new StreamsExercise5Distinct().run();
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
        LOGGER.info("First message time {}, last message time: {}",
                cargoLoadedMessages.get(0).sentAt(), cargoLoadedMessages.get(cargoLoadedMessages.size() - 1).sentAt());

        LOGGER.info("Count of all messages: {}", cargoLoadedMessages.size());

        LOGGER.info("Count of distinct messages: {}", cargoLoadedMessages.stream().distinct().count());

        List<Message<CargoLoadedMessageContent>> latestMessagesPerVehicle = cargoLoadedMessages.stream()
                .sorted(Comparator.comparing(Message::sentAt))
                .sorted(Comparator.reverseOrder())
                .filter(distinctByVehicleId())
                .toList();
        LOGGER.info("Latest messages per vehicle: {}", latestMessagesPerVehicle);

    }

    private Predicate<? super Message<CargoLoadedMessageContent>> distinctByVehicleId()
    {
        Set<UUID> vehicleIds = ConcurrentHashMap.newKeySet();

        return message ->
        {
            boolean result = vehicleIds.add(message.content().getVehicleId());
            LOGGER.info("res" + result);
            LOGGER.info("ids" + vehicleIds);
            return result;
        };
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