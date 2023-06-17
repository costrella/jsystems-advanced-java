package pl.jsystems.advancedjava.lambdas.exercises.e4methodreference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.lambdas.exercises.e4methodreference.contents.VehicleMessageContent;
import pl.jsystems.advancedjava.lambdas.exercises.e4methodreference.message.Message;
import pl.jsystems.advancedjava.lambdas.exercises.e4methodreference.contents.CargoMessageContent;
import pl.jsystems.advancedjava.lambdas.exercises.e4methodreference.contents.MessageContent;

import java.time.Instant;

class MessageLogger
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageLogger.class);

    void logReceived(Message<? extends MessageContent> message)
    {
        // this works, and is quite simple solution, but not the best design.
        // also, please notice that the issue is that for CargoLoadedContent / CargoUnloadedContent
        // we're logging the vehicle info, not the cargo info.
        // Alternatively, we could log the message twice.
        // Maybe, later on, we'll find a way of doing it better way to handle this.
        // BTW if we used 'preview' mode, we could do a new and fancy switch pattern matching approach here.
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
                message.content().getCargoId(),
                message.sentAt(),
                Instant.now());
    }
}
