package pl.jsystems.advancedjava.streams.solutions.s7minmaxpeek;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.streams.solutions.s7minmaxpeek.contents.CargoLoadedMessageContent;
import pl.jsystems.advancedjava.streams.solutions.s7minmaxpeek.message.Message;
import pl.jsystems.advancedjava.streams.solutions.s7minmaxpeek.receivers.CargoLoadedMessageReceiver;
import pl.jsystems.advancedjava.streams.solutions.s7minmaxpeek.receivers.CargoUnloadedMessageReceiver;
import pl.jsystems.advancedjava.streams.solutions.s7minmaxpeek.receivers.GPSTrackingMessageReceiver;
import pl.jsystems.advancedjava.streams.solutions.s7minmaxpeek.repositories.CargoLoadedMessageRepository;
import pl.jsystems.advancedjava.streams.solutions.s7minmaxpeek.repositories.CargoUnloadedMessageRepository;
import pl.jsystems.advancedjava.streams.solutions.s7minmaxpeek.repositories.GPSTrackingMessageRepository;

import java.math.BigDecimal;
import java.util.List;

class StreamsExercise7MinMaxPeek
{
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamsExercise7MinMaxPeek.class);

    public static void main(String[] args)
    {
        new StreamsExercise7MinMaxPeek().run();
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

        BigDecimal heaviest = cargoLoadedMessages.stream()
                .map(message -> message.content().getCargoLoadInKgs())
                .peek(weight -> LOGGER.info("Weight: " + weight))
                .max(BigDecimal::compareTo)
                .orElseThrow(() -> new RuntimeException("No messages found!"));
        LOGGER.info("Heaviest content: {}", heaviest);


        Long fastest = cargoLoadedMessages.stream()
                .map(message -> message.content().getLoadingTimeTakenInSeconds())
                .peek(loadingTime -> LOGGER.info("Loading time: " + loadingTime))
                .min(Long::compareTo)
                .orElse(0L);
        LOGGER.info("Fastest loading time: {}", fastest);
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