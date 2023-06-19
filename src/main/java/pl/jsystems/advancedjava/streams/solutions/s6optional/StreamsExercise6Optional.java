package pl.jsystems.advancedjava.streams.solutions.s6optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.streams.solutions.s6optional.contents.CargoLoadedMessageContent;
import pl.jsystems.advancedjava.streams.solutions.s6optional.message.Message;
import pl.jsystems.advancedjava.streams.solutions.s6optional.receivers.CargoLoadedMessageReceiver;
import pl.jsystems.advancedjava.streams.solutions.s6optional.receivers.CargoUnloadedMessageReceiver;
import pl.jsystems.advancedjava.streams.solutions.s6optional.receivers.GPSTrackingMessageReceiver;
import pl.jsystems.advancedjava.streams.solutions.s6optional.repositories.CargoLoadedMessageRepository;
import pl.jsystems.advancedjava.streams.solutions.s6optional.repositories.CargoUnloadedMessageRepository;
import pl.jsystems.advancedjava.streams.solutions.s6optional.repositories.GPSTrackingMessageRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

class StreamsExercise6Optional
{
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamsExercise6Optional.class);

    public static void main(String[] args)
    {
        new StreamsExercise6Optional().run();
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

        UUID validMessageId = cargoLoadedMessages.get(0).id();

        // point 2
        Long someTimeUsedForLoading = CARGO_LOADED_MESSAGE_REPOSITORY.findById(validMessageId)
                .map(Message::content)
                .map(CargoLoadedMessageContent::getLoadingTimeTakenInSeconds)
                .orElse(120L);
        LOGGER.info("Some time: " + someTimeUsedForLoading);

        // point 3
        CARGO_LOADED_MESSAGE_REPOSITORY.findById(validMessageId)
                .map(Message::content)
                .map(CargoLoadedMessageContent::getCargoLoadInKgs)
                .ifPresentOrElse(weight -> LOGGER.info("Some weight: " + weight), () ->
                {
                    throw new IllegalStateException("I wanted something more!");
                });

        // point 3 alternative
        Optional<BigDecimal> weightOptional = CARGO_LOADED_MESSAGE_REPOSITORY.findById(validMessageId)
                .map(Message::content)
                .map(CargoLoadedMessageContent::getCargoLoadInKgs);
        if (weightOptional.isPresent())
        {
            LOGGER.info("Some weight: " + weightOptional.get());
        }
        else
        {
            throw new IllegalStateException("I wanted something more!");
        }

        weightOptional = CARGO_LOADED_MESSAGE_REPOSITORY.findById(validMessageId)
                .map(Message::content)
                .map(CargoLoadedMessageContent::getCargoLoadInKgs);
        if (weightOptional.isEmpty())
        {
            LOGGER.info("Message got lost");
        }
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
