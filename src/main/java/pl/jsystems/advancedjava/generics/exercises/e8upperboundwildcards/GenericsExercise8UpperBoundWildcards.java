package pl.jsystems.advancedjava.generics.exercises.e8upperboundwildcards;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

class GenericsExercise8UpperBoundWildcards
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericsExercise8UpperBoundWildcards.class);

    public static void main(String[] args)
    {
        new GenericsExercise8UpperBoundWildcards().run();
    }

    private void run()
    {
        LOGGER.info("Time to handle some messages!");
        GPSTrackingMessageContent gpsTrackingMessageContent1 = new GPSTrackingMessageContent();
        GPSTrackingMessageContent gpsTrackingMessageContent2 = new GPSTrackingMessageContent();
        CargoReadyForLoadingMessageContent cargoReadyForLoadingContentMessageContent = new CargoReadyForLoadingMessageContent();
        CargoLoadedMessageContent cargoLoadedMessageContent = new CargoLoadedMessageContent();
        CargoUnloadedMessageContent cargoUnloadedMessageContent = new CargoUnloadedMessageContent();

        Message<GPSTrackingMessageContent> gpsTrackingMessage1 = new Message<>(
                UUID.randomUUID(),
                gpsTrackingMessageContent1,
                Instant.now().minus(2, ChronoUnit.MINUTES)
        );

        Message<CargoReadyForLoadingMessageContent> cargoReadyForLoadingMessage = new Message<>(
                UUID.randomUUID(),
                cargoReadyForLoadingContentMessageContent,
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

        // TODO stuff...
    }
}