package pl.jsystems.advancedjava.generics.solutions.s13extendinggenericclasses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

class GenericsExercise13ExtendingGenericClasses
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericsExercise13ExtendingGenericClasses.class);

    public static void main(String[] args)
    {
        new GenericsExercise13ExtendingGenericClasses().run();
    }

    private void run()
    {
        LOGGER.info("Time to handle some messages!");
        GPSTrackingMessageContent gpsTrackingMessageContent1 = new GPSTrackingMessageContent();
        GPSTrackingMessageContent gpsTrackingMessageContent2 = new GPSTrackingMessageContent();
        CargoReadyForLoadingMessageContent cargoReadyForLoadingMessageContent = new CargoReadyForLoadingMessageContent();
        CargoLoadedMessageContent cargoLoadedMessageContent = new CargoLoadedMessageContent();
        CargoUnloadedMessageContent cargoUnloadedMessageContent = new CargoUnloadedMessageContent();

        Message<GPSTrackingMessageContent> gpsTrackingMessage1 = new Message<>(
                UUID.randomUUID(),
                gpsTrackingMessageContent1,
                Instant.now().minus(2, ChronoUnit.MINUTES)
        );

        Message<CargoReadyForLoadingMessageContent> cargoReadyForLoadingMessage = new Message<>(
                UUID.randomUUID(),
                cargoReadyForLoadingMessageContent,
                Instant.now().minus(1, ChronoUnit.DAYS)
        );

        Message<CargoLoadedMessageContent> cargoLoadedMessage = new Message<>(
                UUID.randomUUID(),
                cargoLoadedMessageContent,
                Instant.now().minus(1, ChronoUnit.HOURS)
        );

        Message<CargoUnloadedMessageContent> cargoUnloadedMessage = new Message<>(
                UUID.randomUUID(),
                cargoUnloadedMessageContent,
                Instant.now().minus(1, ChronoUnit.MINUTES)
        );

        Message<GPSTrackingMessageContent> gpsTrackingMessage2 = new Message<>(
                UUID.randomUUID(),
                gpsTrackingMessageContent2,
                Instant.now().minus(1, ChronoUnit.SECONDS)
        );

        // Solution
        LOGGER.info("Comparing sentAt time between {}, {} - result {}",
                cargoLoadedMessage, gpsTrackingMessage1,
                cargoLoadedMessage.compareTo(gpsTrackingMessage1));
        LOGGER.info("Comparing sentAt time between {}, {} - result {}",
                cargoUnloadedMessage, cargoLoadedMessage,
                cargoUnloadedMessage.compareTo(cargoLoadedMessage));

        var cargoLoadMessageComparator = new CargoLoadMessageComparator();
        LOGGER.info("Comparing load between: {}, {} - result: {}",
                cargoUnloadedMessageContent, cargoReadyForLoadingMessageContent,
                cargoLoadMessageComparator.compare(cargoUnloadedMessageContent, cargoReadyForLoadingMessageContent));
        LOGGER.info("Comparing load between: {}, {} - result: {}",
                cargoUnloadedMessageContent, cargoLoadedMessageContent,
                cargoLoadMessageComparator.compare(cargoUnloadedMessageContent, cargoLoadedMessageContent));

        var loadTimeComparator = new CargoLoadingTimeTakenComparator();
        LOGGER.info("Comparing load time between: {}, {} - result: {}",
                cargoUnloadedMessageContent, cargoReadyForLoadingMessageContent,
                loadTimeComparator.compare(cargoUnloadedMessageContent, cargoLoadedMessageContent));
    }
}
