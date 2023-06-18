package pl.jsystems.advancedjava.lambdas.exercises.e5predicateinterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.lambdas.exercises.e5predicateinterface.contents.CargoMessageContent;
import pl.jsystems.advancedjava.lambdas.exercises.e5predicateinterface.contents.MessageContent;
import pl.jsystems.advancedjava.lambdas.exercises.e5predicateinterface.contents.VehicleMessageContent;
import pl.jsystems.advancedjava.lambdas.exercises.e5predicateinterface.message.Message;

import java.time.Instant;

class MessageLogger
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageLogger.class);

    void logReceived(Message<? extends MessageContent> message)
    {
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
                message.content().getCargoId(),
                message.sentAt(),
                Instant.now());
    }
}
