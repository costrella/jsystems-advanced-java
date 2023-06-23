package pl.jsystems.advancedjava.streams.solutions.s11grouping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.streams.solutions.s11grouping.contents.CargoLoadedMessageContent;
import pl.jsystems.advancedjava.streams.solutions.s11grouping.contents.CargoUnloadedMessageContent;
import pl.jsystems.advancedjava.streams.solutions.s11grouping.message.Message;
import pl.jsystems.advancedjava.streams.solutions.s11grouping.receivers.CargoLoadedMessageReceiver;
import pl.jsystems.advancedjava.streams.solutions.s11grouping.receivers.CargoUnloadedMessageReceiver;
import pl.jsystems.advancedjava.streams.solutions.s11grouping.receivers.GPSTrackingMessageReceiver;
import pl.jsystems.advancedjava.streams.solutions.s11grouping.repositories.CargoLoadedMessageRepository;
import pl.jsystems.advancedjava.streams.solutions.s11grouping.repositories.CargoUnloadedMessageRepository;
import pl.jsystems.advancedjava.streams.solutions.s11grouping.repositories.GPSTrackingMessageRepository;

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

        Map<LocalDate, Long> totalUnloadingTimePerDay = cargoUnloadedMessages.stream()
                .collect(Collectors.groupingBy(message -> instantToLocalDate(message.sentAt()),
                        Collectors.summingLong(message -> message.content().getLoadingTimeTakenInSeconds())));
        LOGGER.info("Total time spent for unloading by date: {}", totalUnloadingTimePerDay);

        Map<LocalDate, Long> totalUnloadingTimePerDayByReducing = cargoUnloadedMessages.stream()
                .collect(Collectors.groupingBy(message -> instantToLocalDate(message.sentAt()),
                        Collectors.reducing(0L, message1 -> message1.content().getLoadingTimeTakenInSeconds(), Long::sum)));
        LOGGER.info("*Total time spent for unloading by date: {}", totalUnloadingTimePerDayByReducing);

        Map<LocalDate, Double> averageUnloadingTimePerDay = cargoUnloadedMessages.stream()
                .collect(Collectors.groupingBy(message -> instantToLocalDate(message.sentAt()),
                        Collectors.averagingLong(message -> message.content().getLoadingTimeTakenInSeconds())));
        LOGGER.info("Average time spent for unloading by date: {}", averageUnloadingTimePerDay);

        Map<LocalDate, Optional<Message<CargoLoadedMessageContent>>> messageWithMinCargoPickedUpWeightPerDay = cargoLoadedMessages.stream()
                .collect(Collectors.groupingBy(message -> instantToLocalDate(message.sentAt()),
                        Collectors.minBy(Comparator.comparing(message -> message.content().getCargoLoadInKgs()))));
        LOGGER.info("Message with min cargo picked up by date: {}", messageWithMinCargoPickedUpWeightPerDay);

        Map<LocalDate, Optional<BigDecimal>> minCargoPickedUpWeightPerDay = cargoLoadedMessages.stream()
                .collect(Collectors.groupingBy(message -> instantToLocalDate(message.sentAt()),
                        Collectors.mapping(message -> message.content().getCargoLoadInKgs(), Collectors.minBy(BigDecimal::compareTo))));
        LOGGER.info("*Min cargo picked up by date: {}", minCargoPickedUpWeightPerDay);

        Map<LocalDate, Optional<Message<CargoUnloadedMessageContent>>> messageWithMaxCargoPickedUpWeightPerDay = cargoUnloadedMessages.stream()
                .collect(Collectors.groupingBy(message -> instantToLocalDate(message.sentAt()),
                        Collectors.maxBy(Comparator.comparing(message -> message.content().getCargoLoadInKgs()))));
        LOGGER.info("Message with max cargo picked up by date: {}", messageWithMaxCargoPickedUpWeightPerDay);

        Map<LocalDate, Optional<BigDecimal>> maxCargoPickedUpWeightPerDay = cargoUnloadedMessages.stream()
                .collect(Collectors.groupingBy(message -> instantToLocalDate(message.sentAt()),
                        Collectors.mapping(message -> message.content().getCargoLoadInKgs(), Collectors.maxBy(BigDecimal::compareTo))));
        LOGGER.info("*Max cargo picked up by date: {}", maxCargoPickedUpWeightPerDay);

        Map<LocalDate, List<UUID>> deliveringVehiclesPerDay = cargoUnloadedMessages.stream()
                .collect(Collectors.groupingBy(message -> instantToLocalDate(message.sentAt()),
                        Collectors.mapping(message -> message.content().getVehicleId(), Collectors.toList())));
        LOGGER.info("Vehicles that delivered something by date: {}", deliveringVehiclesPerDay);

        Map<LocalDate, List<UUID>> pickingUpVehiclesPerDay = cargoLoadedMessages.stream()
                .collect(Collectors.groupingBy(message -> LocalDate.ofInstant(message.sentAt(), ZoneId.systemDefault()),
                        Collectors.mapping(message -> message.content().getVehicleId(), Collectors.toList())));
        LOGGER.info("Vehicles that picked something up by date: {}", pickingUpVehiclesPerDay);
    }

    private static LocalDate instantToLocalDate(final Instant message)
    {
        return LocalDate.ofInstant(message, ZoneId.systemDefault());
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
