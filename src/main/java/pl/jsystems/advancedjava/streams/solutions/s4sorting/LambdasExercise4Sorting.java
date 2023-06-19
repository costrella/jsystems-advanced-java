package pl.jsystems.advancedjava.streams.solutions.s4sorting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.streams.solutions.s4sorting.comparators.CargoLoadWeightComparator;
import pl.jsystems.advancedjava.streams.solutions.s4sorting.comparators.CargoLoadingTimeTakenComparator;
import pl.jsystems.advancedjava.streams.solutions.s4sorting.contents.CargoLoadedMessageContent;
import pl.jsystems.advancedjava.streams.solutions.s4sorting.message.Message;
import pl.jsystems.advancedjava.streams.solutions.s4sorting.receivers.CargoLoadedMessageReceiver;
import pl.jsystems.advancedjava.streams.solutions.s4sorting.receivers.CargoUnloadedMessageReceiver;
import pl.jsystems.advancedjava.streams.solutions.s4sorting.receivers.GPSTrackingMessageReceiver;
import pl.jsystems.advancedjava.streams.solutions.s4sorting.repositories.CargoLoadedMessageRepository;
import pl.jsystems.advancedjava.streams.solutions.s4sorting.repositories.CargoUnloadedMessageRepository;
import pl.jsystems.advancedjava.streams.solutions.s4sorting.repositories.GPSTrackingMessageRepository;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

class LambdasExercise4Sorting
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LambdasExercise4Sorting.class);

    public static void main(String[] args)
    {
        new LambdasExercise4Sorting().run();
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

        String joinedTimestamps = cargoLoadedMessages.stream()
                .sorted()
                .map(Message::sentAt)
                .map(Instant::toString)
                .collect(Collectors.joining(","));
        LOGGER.info("After sorting list {};", joinedTimestamps);

        String joinedLoadingTimes = cargoLoadedMessages.stream()
                .map(Message::content)
                .sorted(new CargoLoadingTimeTakenComparator())
                .map(CargoLoadedMessageContent::getLoadingTimeTakenInSeconds)
                .map(Object::toString)
                .collect(Collectors.joining(","));
        LOGGER.info("After sorting list by loading time with comparator {};", joinedLoadingTimes);

        String joinedLoadingTimesAgain = cargoLoadedMessages.stream()
                .map(Message::content)
                .map(CargoLoadedMessageContent::getLoadingTimeTakenInSeconds)
                .sorted()
                .map(Object::toString)
                .collect(Collectors.joining(","));
        LOGGER.info("After sorting list by loading time again without comparator {};", joinedLoadingTimesAgain);

        String joinedWeights = cargoLoadedMessages.stream()
                .map(Message::content)
                .sorted(new CargoLoadWeightComparator())
                .map(CargoLoadedMessageContent::getCargoLoadInKgs)
                .map(Object::toString)
                .collect(Collectors.joining(","));
        LOGGER.info("After sorting list by weight with comparator {};", joinedWeights);

        String joinedWeightsAgain = cargoLoadedMessages.stream()
                .map(Message::content)
                .map(CargoLoadedMessageContent::getCargoLoadInKgs)
                .sorted()
                .map(Object::toString)
                .collect(Collectors.joining(","));
        LOGGER.info("After sorting list by weight without comparator {};", joinedWeightsAgain);
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
