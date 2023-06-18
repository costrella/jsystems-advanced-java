package pl.jsystems.advancedjava.lambdas.solutions.s8functioninterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.lambdas.solutions.s8functioninterface.contents.CargoMessageContent;
import pl.jsystems.advancedjava.lambdas.solutions.s8functioninterface.contents.MessageContent;
import pl.jsystems.advancedjava.lambdas.solutions.s8functioninterface.contents.VehicleMessageContent;
import pl.jsystems.advancedjava.lambdas.solutions.s8functioninterface.message.Message;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

class MessageLogger
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageLogger.class);
    private static final Predicate<MessageContent> IS_VEHICLE_MESSAGE_CONTENT = content -> content instanceof VehicleMessageContent;
    private static final Predicate<MessageContent> IS_CARGO_MESSAGE_CONTENT = content -> content instanceof CargoMessageContent;

    private static final Function<VehicleMessageContent, String> VEHICLE_MESSAGE_LOGGER =
            content -> "Vehicle id: " + content.getVehicleId();
    private static final Function<CargoMessageContent, String> CARGO_MESSAGE_LOGGER =
            content -> "Content id: " + content.getCargoId();

    private final List<MessageDetailsExtractor> extractors = List.of(
            new MessageDetailsExtractor(IS_VEHICLE_MESSAGE_CONTENT, VEHICLE_MESSAGE_LOGGER),
            new MessageDetailsExtractor(IS_CARGO_MESSAGE_CONTENT, CARGO_MESSAGE_LOGGER)
    );

    void logReceived(Message<? extends MessageContent> message)
    {
        List<String> messageDetails = new ArrayList<>();

        for (MessageDetailsExtractor extractor : extractors)
        {
            String details = extractor.extractFrom(message.content());
            if (details != null)
            {
                messageDetails.add(details);
            }
        }

        LOGGER.info("Received new message with id {} of type {}. Sent at: {}, received at {}. Details: {}.",
                message.id(),
                message.content().getClass().getSimpleName(),
                message.sentAt(),
                Instant.now(),
                messageDetails);
    }

    private static class MessageDetailsExtractor
    {
        private final Predicate<MessageContent> isKnownContent;
        private final Function<MessageContent, String> extractor;

        MessageDetailsExtractor(Predicate<MessageContent> isKnownContent, Function<? extends MessageContent, String> extractor)
        {
            this.isKnownContent = isKnownContent;
            this.extractor = (Function<MessageContent, String>) extractor;
        }

        private String extractFrom(MessageContent content)
        {
            if (isKnownContent.test(content))
            {
                return extractor.apply(content);
            }
            return null;
        }
    }
}
