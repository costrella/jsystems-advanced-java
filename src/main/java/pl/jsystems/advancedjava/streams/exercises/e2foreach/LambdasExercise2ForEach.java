package pl.jsystems.advancedjava.streams.exercises.e2foreach;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.streams.exercises.e2foreach.contents.CargoLoadedMessageContent;
import pl.jsystems.advancedjava.streams.exercises.e2foreach.message.Message;
import pl.jsystems.advancedjava.streams.exercises.e2foreach.receivers.CargoLoadedMessageReceiver;
import pl.jsystems.advancedjava.streams.exercises.e2foreach.receivers.CargoUnloadedMessageReceiver;
import pl.jsystems.advancedjava.streams.exercises.e2foreach.receivers.GPSTrackingMessageReceiver;
import pl.jsystems.advancedjava.streams.exercises.e2foreach.repositories.CargoLoadedMessageRepository;
import pl.jsystems.advancedjava.streams.exercises.e2foreach.repositories.CargoUnloadedMessageRepository;
import pl.jsystems.advancedjava.streams.exercises.e2foreach.repositories.GPSTrackingMessageRepository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

class LambdasExercise2ForEach
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LambdasExercise2ForEach.class);

    public static void main(String[] args)
    {
        new LambdasExercise2ForEach().run();
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

        long numberOfMessagesInLast5Minutes = cargoLoadedMessages.stream()
                .filter(message -> Instant.now().minus(5, ChronoUnit.MINUTES).isBefore(message.sentAt()))
                .count();
        LOGGER.info("Number of messages in last 5 minutes: {}", numberOfMessagesInLast5Minutes);

        List<Message<CargoLoadedMessageContent>> messagesInLast10Minutes = cargoLoadedMessages.stream()
                .filter(message -> Instant.now().minus(10, ChronoUnit.MINUTES).isBefore(message.sentAt()))
                .toList();
        LOGGER.info("Messages in last 10 minutes count: {}", messagesInLast10Minutes.size());
        LOGGER.info("Messages in last 10 minutes example timestamp: {}",
                messagesInLast10Minutes.get(0) != null ? messagesInLast10Minutes.get(0).sentAt() : "None");
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
