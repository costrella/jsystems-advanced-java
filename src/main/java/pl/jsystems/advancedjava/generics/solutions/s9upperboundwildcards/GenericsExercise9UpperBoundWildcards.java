package pl.jsystems.advancedjava.generics.solutions.s9upperboundwildcards;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

class GenericsExercise9UpperBoundWildcards
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericsExercise9UpperBoundWildcards.class);

    public static void main(String[] args)
    {
        new GenericsExercise9UpperBoundWildcards().run();
    }

    private void run()
    {
        LOGGER.info("Time to handle some messages!");
        GPSTrackingMessageContent gpsTrackingMessageContent1 = new GPSTrackingMessageContent();
        GPSTrackingMessageContent gpsTrackingMessageContent2 = new GPSTrackingMessageContent();
        CargoReadyForLoadingMessageContent cargoReadyForLoadingMessageContentContent = new CargoReadyForLoadingMessageContent();
        CargoLoadedMessageContent cargoLoadedMessageContent = new CargoLoadedMessageContent();
        CargoUnloadedMessageContent cargoUnloadedMessageContent = new CargoUnloadedMessageContent();

        Message<GPSTrackingMessageContent> gpsTrackingMessage1 = new Message<>(
                UUID.randomUUID(),
                gpsTrackingMessageContent1,
                Instant.now().minus(2, ChronoUnit.MINUTES)
        );

        Message<CargoReadyForLoadingMessageContent> cargoReadyForLoadingMessage = new Message<>(
                UUID.randomUUID(),
                cargoReadyForLoadingMessageContentContent,
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

        logReceived(gpsTrackingMessage1);
        logReceived(cargoReadyForLoadingMessage);
        logReceived(cargoLoadedMessage);
        logReceived(cargoUnloadedMessage);
        logReceived(gpsTrackingMessage2);

        /*
        // The below is part 1 of the solution, but not needed after other parts have been added in 'logReceived'.
        logReceivedVehicleMessage(gpsTrackingMessage1);
        logReceivedVehicleMessage(cargoLoadedMessage);
        logReceivedVehicleMessage(cargoUnloadedMessage);
        logReceivedVehicleMessage(gpsTrackingMessage2);

        logReceivedCargoMessage(cargoReadyForLoadingMessage);
        logReceivedCargoMessage(cargoLoadedMessage);
        logReceivedCargoMessage(cargoUnloadedMessage);
        */
    }

    private void logReceived(Message<? extends MessageContent> message)
    {
        // this works, and is quite simple solution, but not the best design.
        // also, please notice that the issue is that for CargoLoadedContent / CargoUnloadedContent
        // we're logging the vehicle info, not the cargo info.
        // Alternatively, we could log the message twice.
        // Maybe, later on, we'll find a way of doing it better way to handle this.
        // BTW in Java 21 we can use a new fancy switch pattern matching feature.
        // Please look at the bottom of the file to see how it could look like.
        if (message.content() instanceof VehicleMessageContent)
        {
            //noinspection unchecked
            logReceivedVehicleMessage((Message<? extends VehicleMessageContent>) message);
        }
        else if (message.content() instanceof CargoMessageContent)
        {
            //noinspection unchecked
            logReceivedCargoMessage((Message<? extends CargoMessageContent>) message);
        }
        else
        {
            LOGGER.info("Received new message with id {} of type {}. Sent at: {}, received at {}.",
                    message.id(),
                    message.content().getClass().getSimpleName(),
                    message.sentAt(),
                    Instant.now());
        }
    }

    private void logReceivedVehicleMessage(Message<? extends VehicleMessageContent> message)
    {
        LOGGER.info("Received new vehicle message with with id {} and vehicle id {}. Sent at: {}, received at {}.",
                message.id(),
                message.content().getVehicleId(),
                message.sentAt(),
                Instant.now());
    }

    private void logReceivedCargoMessage(Message<? extends CargoMessageContent> message)
    {
        LOGGER.info("Received new cargo message with with id {} and cargo id {}. Sent at: {}, received at {}.",
                message.id(),
                message.content().getCargoUUID(),
                message.sentAt(),
                Instant.now());
    }
}

/*
        switch (message.content())
        {
            case VehicleMessageContent ignored ->
                    logReceivedVehicleMessage((Message<? extends VehicleMessageContent>) message);
            case CargoMessageContent ignored ->
                    logReceivedCargoMessage((Message<? extends CargoMessageContent>) message);
            default ->
                LOGGER.info("Received new message with id {} of type {}. Sent at: {}, received at {}.",
                        message.id(),
                        message.content().getClass().getSimpleName(),
                        message.sentAt(),
                        Instant.now());
        }
 */