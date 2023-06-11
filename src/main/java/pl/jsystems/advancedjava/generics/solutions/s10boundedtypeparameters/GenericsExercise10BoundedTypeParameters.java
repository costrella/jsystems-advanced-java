package pl.jsystems.advancedjava.generics.solutions.s10boundedtypeparameters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

class GenericsExercise10BoundedTypeParameters
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericsExercise10BoundedTypeParameters.class);

    public static void main(String[] args)
    {
        new GenericsExercise10BoundedTypeParameters().run();
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

        Message<GPSTrackingMessageContent> mostRecentMessage = mostRecentMessageOf(
                gpsTrackingMessage1,
                gpsTrackingMessage2
        );
        LOGGER.info("Most recent gps message: {}", mostRecentMessage);
        LOGGER.info("We cannot compare any other messages - we don't have any other messages with same type.");

        // exercise point 4 - the below doesn't work - types are different
        // var heaviest = messageWithHeaviestCargo(cargoLoadedMessage, cargoUnloadedMessage);

        // exercise point 4 - approach1
        // approach1 - add second message of the same type that we already have.
        Message<CargoLoadedMessageContent> secondCargoLoadedMessage = new Message<>(
                UUID.randomUUID(),
                new CargoLoadedMessageContent(),
                Instant.now().minus(1, ChronoUnit.MINUTES)
        );
        Message<CargoLoadedMessageContent> approach1Result = messageWithHeaviestCargoOf(
                cargoLoadedMessage,
                secondCargoLoadedMessage
        );
        LOGGER.info("Approach 1 - out of two messages, the one with heaviest cargo is: {}", approach1Result);

        // exercise point 4 - approach2
        // approach2 - don't use bounded type parameter
        Message<? extends CargoMessageContent> approach2Result = approach2toMessageWithHeaviestCargoOf(
                cargoLoadedMessage,
                cargoUnloadedMessage
        );
        LOGGER.info("Approach 2 - out of two messages, the one with heaviest cargo is: {}", approach2Result);

        // exercise point 4 - approach3
        // approach3 - don't use bounded type parameter
        // remember, we cannot do:
        // Message<CargoMessageContent> cargoLoadedMessageCopy = cargoLoadedMessage;
        Message<CargoMessageContent> cargoLoadedMessageCopy = new Message<>(
                cargoLoadedMessage.id(),
                cargoLoadedMessage.content(),
                cargoLoadedMessage.sentAt()
        );

        Message<CargoMessageContent> cargoUnloadedMessageCopy = new Message<>(
                cargoUnloadedMessage.id(),
                cargoUnloadedMessage.content(),
                cargoUnloadedMessage.sentAt()
        );

        Message<CargoMessageContent> approach3Result = messageWithHeaviestCargoOf(
                cargoLoadedMessageCopy,
                cargoUnloadedMessageCopy
        );
        LOGGER.info("Approach 3 - out of two messages, the one with heaviest cargo is: {}", approach3Result);
        LOGGER.info("We can use the same approaches to the mostRecentMessageOf method.");
    }

    private <T extends MessageContent> Message<T> mostRecentMessageOf(Message<T> message1, Message<T> message2)
    {
        if (message1.sentAt().compareTo(message2.sentAt()) >= 0)
        {
            return message1;
        }
        return message2;
    }

    private <T extends CargoMessageContent> Message<T> messageWithHeaviestCargoOf(Message<T> message1, Message<T> message2)
    {
        if (message1.content().getCargoLoadInKgs().compareTo(message2.content().getCargoLoadInKgs()) >= 0)
        {
            return message1;
        }
        return message2;
    }

    private Message<? extends CargoMessageContent> approach2toMessageWithHeaviestCargoOf(Message<? extends CargoMessageContent> message1, Message<? extends CargoMessageContent> message2)
    {
        if (message1.content().getCargoLoadInKgs().compareTo(message2.content().getCargoLoadInKgs()) >= 0)
        {
            return message1;
        }
        return message2;
    }

    private void logReceived(Message<? extends MessageContent> message)
    {
        // this works, and is quite simple solution, but not the best design.
        // also, please notice that the issue is that for CargoLoadedContent / CargoUnloadedContent
        // we're logging the vehicle info, not the cargo info.
        // Alternatively, we could log the message twice.
        // Maybe, later on, we'll find a way of doing it better way to handle this.
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
