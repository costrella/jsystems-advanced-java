package pl.jsystems.advancedjava.lambdas.solutions.s6biconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.lambdas.solutions.s6biconsumer.contents.CargoMessageContent;
import pl.jsystems.advancedjava.lambdas.solutions.s6biconsumer.contents.MessageContent;
import pl.jsystems.advancedjava.lambdas.solutions.s6biconsumer.contents.VehicleMessageContent;
import pl.jsystems.advancedjava.lambdas.solutions.s6biconsumer.message.Message;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

class MessageLogger
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageLogger.class);
    private static final Predicate<MessageContent> IS_VEHICLE_MESSAGE_CONTENT = content -> content instanceof VehicleMessageContent;
    private static final Predicate<MessageContent> IS_CARGO_MESSAGE_CONTENT = content -> content instanceof CargoMessageContent;

    private static final BiConsumer<List<String>, Message<? extends VehicleMessageContent>> VEHICLE_MESSAGE_LOGGER =
            (details, message) -> details.add("Vehicle id: " + message.content().getVehicleId());
    private static final BiConsumer<List<String>, Message<? extends CargoMessageContent>> CARGO_MESSAGE_LOGGER =
            (details, message) -> details.add("Vehicle id: " + message.content().getCargoId());

    void logReceived(Message<? extends MessageContent> message)
    {
        List<String> messageDetails = new ArrayList<>();
        if (IS_VEHICLE_MESSAGE_CONTENT.test(message.content()))
        {
            //noinspection unchecked
            VEHICLE_MESSAGE_LOGGER.accept(messageDetails, (Message<? extends VehicleMessageContent>) message);
        }

        if (IS_CARGO_MESSAGE_CONTENT.test(message.content()))
        {
            //noinspection unchecked
            CARGO_MESSAGE_LOGGER.accept(messageDetails, (Message<? extends CargoMessageContent>) message);
        }

        LOGGER.info("Received new message with id {} of type {}. Sent at: {}, received at {}. Details: {}.",
                message.id(),
                message.content().getClass().getSimpleName(),
                message.sentAt(),
                Instant.now(),
                messageDetails);
    }
}
