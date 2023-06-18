package pl.jsystems.advancedjava.lambdas.solutions.s5predicateinterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.lambdas.solutions.s5predicateinterface.contents.CargoMessageContent;
import pl.jsystems.advancedjava.lambdas.solutions.s5predicateinterface.contents.MessageContent;
import pl.jsystems.advancedjava.lambdas.solutions.s5predicateinterface.contents.VehicleMessageContent;
import pl.jsystems.advancedjava.lambdas.solutions.s5predicateinterface.message.Message;

import java.time.Instant;
import java.util.function.Consumer;
import java.util.function.Predicate;

class MessageLogger
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageLogger.class);
    private static final Predicate<MessageContent> IS_VEHICLE_MESSAGE_CONTENT = content -> content instanceof VehicleMessageContent;
    private static final Predicate<MessageContent> IS_CARGO_MESSAGE_CONTENT = content -> content instanceof CargoMessageContent;

    private static final Consumer<Message<? extends VehicleMessageContent>> VEHICLE_MESSAGE_LOGGER = MessageLogger::logReceivedVehicleMessage;
    private static final Consumer<Message<? extends CargoMessageContent>> CARGO_MESSAGE_LOGGER = MessageLogger::logReceivedCargoMessage;

    void logReceived(Message<? extends MessageContent> message)
    {
        if (IS_VEHICLE_MESSAGE_CONTENT.test(message.content()))
        {
            //noinspection unchecked
            VEHICLE_MESSAGE_LOGGER.accept((Message<? extends VehicleMessageContent>) message);
        }
        else if (IS_CARGO_MESSAGE_CONTENT.test(message.content()))
        {
            //noinspection unchecked
            CARGO_MESSAGE_LOGGER.accept((Message<? extends CargoMessageContent>) message);
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

    private static void logReceivedVehicleMessage(Message<? extends VehicleMessageContent> message)
    {
        LOGGER.info("Received new vehicle message with with id {} and vehicle id {}. Sent at: {}, received at {}.",
                message.id(),
                message.content().getVehicleId(),
                message.sentAt(),
                Instant.now());
    }

    private static void logReceivedCargoMessage(Message<? extends CargoMessageContent> message)
    {
        LOGGER.info("Received new cargo message with with id {} and cargo id {}. Sent at: {}, received at {}.",
                message.id(),
                message.content().getCargoId(),
                message.sentAt(),
                Instant.now());
    }
}
