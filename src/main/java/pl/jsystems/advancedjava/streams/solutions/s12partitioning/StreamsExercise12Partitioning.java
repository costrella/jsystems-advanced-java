package pl.jsystems.advancedjava.streams.solutions.s12partitioning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.streams.solutions.s12partitioning.contents.CargoLoadedMessageContent;
import pl.jsystems.advancedjava.streams.solutions.s12partitioning.message.Message;
import pl.jsystems.advancedjava.streams.solutions.s12partitioning.receivers.CargoLoadedMessageReceiver;
import pl.jsystems.advancedjava.streams.solutions.s12partitioning.receivers.CargoUnloadedMessageReceiver;
import pl.jsystems.advancedjava.streams.solutions.s12partitioning.receivers.GPSTrackingMessageReceiver;
import pl.jsystems.advancedjava.streams.solutions.s12partitioning.repositories.CargoLoadedMessageRepository;
import pl.jsystems.advancedjava.streams.solutions.s12partitioning.repositories.CargoUnloadedMessageRepository;
import pl.jsystems.advancedjava.streams.solutions.s12partitioning.repositories.GPSTrackingMessageRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
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

        Map<Boolean, List<Message<CargoLoadedMessageContent>>> messagesFromTodayAndBefore = cargoLoadedMessages.stream()
                .collect(Collectors.partitioningBy(message ->
                                LocalDate.ofInstant(message.sentAt(), ZoneId.systemDefault()).isEqual(LocalDate.now())
                        )
                );
        LOGGER.info("Messages from today: {}", messagesFromTodayAndBefore.get(true));
        LOGGER.info("Messages from before: {}", messagesFromTodayAndBefore.get(false));

        Map<Boolean, Double> averageLoadingTimeFromTodayAndBefore = cargoLoadedMessages.stream()
                .collect(Collectors.partitioningBy(message ->
                                LocalDate.ofInstant(message.sentAt(), ZoneId.systemDefault()).isEqual(LocalDate.now()),
                                Collectors.averagingDouble(message -> message.content().getLoadingTimeTakenInSeconds())
                        )
                );
        LOGGER.info("Average loading time from today: {}", averageLoadingTimeFromTodayAndBefore.get(true));
        LOGGER.info("Average loading time from before: {}", averageLoadingTimeFromTodayAndBefore.get(false));

        Map<Boolean, Double> averageWeightFromTodayAndBefore = cargoLoadedMessages.stream()
                .collect(Collectors.partitioningBy(message ->
                                        LocalDate.ofInstant(message.sentAt(), ZoneId.systemDefault()).isEqual(LocalDate.now()),
                                Collectors.averagingDouble(message -> message.content().getCargoLoadInKgs().doubleValue())
                        )
                );
        LOGGER.info("Average weight from today: {}", averageWeightFromTodayAndBefore.get(true));
        LOGGER.info("Average weight from before: {}", averageWeightFromTodayAndBefore.get(false));
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
