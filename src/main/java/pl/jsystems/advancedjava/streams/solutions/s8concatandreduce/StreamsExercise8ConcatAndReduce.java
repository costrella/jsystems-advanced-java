package pl.jsystems.advancedjava.streams.solutions.s8concatandreduce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.streams.solutions.s8concatandreduce.contents.CargoLoadedMessageContent;
import pl.jsystems.advancedjava.streams.solutions.s8concatandreduce.contents.CargoLoadingMessageContent;
import pl.jsystems.advancedjava.streams.solutions.s8concatandreduce.contents.CargoUnloadedMessageContent;
import pl.jsystems.advancedjava.streams.solutions.s8concatandreduce.message.Message;
import pl.jsystems.advancedjava.streams.solutions.s8concatandreduce.receivers.CargoLoadedMessageReceiver;
import pl.jsystems.advancedjava.streams.solutions.s8concatandreduce.receivers.CargoUnloadedMessageReceiver;
import pl.jsystems.advancedjava.streams.solutions.s8concatandreduce.receivers.GPSTrackingMessageReceiver;
import pl.jsystems.advancedjava.streams.solutions.s8concatandreduce.repositories.CargoLoadedMessageRepository;
import pl.jsystems.advancedjava.streams.solutions.s8concatandreduce.repositories.CargoUnloadedMessageRepository;
import pl.jsystems.advancedjava.streams.solutions.s8concatandreduce.repositories.GPSTrackingMessageRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Stream;

class StreamsExercise8ConcatAndReduce
{
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamsExercise8ConcatAndReduce.class);

    public static void main(String[] args)
    {
        new StreamsExercise8ConcatAndReduce().run();
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

        long totalTimeSpentOnLoading = Stream.concat(unloadedMessages, loadedMessages)
                .map(Message::content)
                .map(CargoLoadingMessageContent::getLoadingTimeTakenInSeconds)
                .reduce(0L, Long::sum);

        LOGGER.info("Total time spent on logging: {}", totalTimeSpentOnLoading);

        Stream<BigDecimal> loadedWeight = cargoLoadedMessages.stream()
                .map(Message::content)
                .map(CargoLoadedMessageContent::getCargoLoadInKgs);

        Stream<BigDecimal> unloaded = cargoUnloadedMessages.stream()
                .map(Message::content)
                .map(CargoUnloadedMessageContent::getCargoLoadInKgs)
                .map(weight -> BigDecimal.valueOf(-1).multiply(weight));

        BigDecimal totalWeight = Stream.concat(loadedWeight, unloaded).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        LOGGER.info("Total weight: {}", totalWeight.setScale(2, RoundingMode.HALF_UP).toPlainString());
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
