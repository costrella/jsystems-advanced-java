package pl.jsystems.advancedjava.lambdas.exercises.e8functioninterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.lambdas.exercises.e8functioninterface.contents.CargoMessageContent;
import pl.jsystems.advancedjava.lambdas.exercises.e8functioninterface.contents.MessageContent;
import pl.jsystems.advancedjava.lambdas.exercises.e8functioninterface.contents.VehicleMessageContent;
import pl.jsystems.advancedjava.lambdas.exercises.e8functioninterface.message.Message;

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

    private static final BiConsumer<List<String>, VehicleMessageContent> VEHICLE_MESSAGE_LOGGER =
            (details, content) -> details.add("Vehicle id: " + content.getVehicleId());
    private static final BiConsumer<List<String>, CargoMessageContent> CARGO_MESSAGE_LOGGER =
            (details, content) -> details.add("Vehicle id: " + content.getCargoId());

    private final List<MessageDetailsExtractor> extractors = List.of(
            new MessageDetailsExtractor(IS_VEHICLE_MESSAGE_CONTENT, VEHICLE_MESSAGE_LOGGER),
            new MessageDetailsExtractor(IS_CARGO_MESSAGE_CONTENT, CARGO_MESSAGE_LOGGER)
    );

    void logReceived(Message<? extends MessageContent> message)
    {
        List<String> messageDetails = new ArrayList<>();

        for (MessageDetailsExtractor extractor : extractors)
        {
            extractor.extractInto(messageDetails, message.content());
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
        private final BiConsumer<List<String>, ? extends MessageContent> extractor;

        MessageDetailsExtractor(Predicate<MessageContent> isKnownContent, BiConsumer<List<String>, ? extends MessageContent> extractor)
        {
            this.isKnownContent = isKnownContent;
            this.extractor = extractor;
        }

        private void extractInto(List<String> messageDetails, MessageContent content)
        {
            if (isKnownContent.test(content))
            {
                ((BiConsumer<List<String>, MessageContent>) extractor).accept(messageDetails, content);
            }
        }
    }
}