package pl.jsystems.advancedjava.streams.exercises.e4sorting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.streams.exercises.e4sorting.contents.CargoMessageContent;
import pl.jsystems.advancedjava.streams.exercises.e4sorting.contents.MessageContent;
import pl.jsystems.advancedjava.streams.exercises.e4sorting.contents.VehicleMessageContent;
import pl.jsystems.advancedjava.streams.exercises.e4sorting.message.Message;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

class MessageLogger
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageLogger.class);

    private static final Function<VehicleMessageContent, String> VEHICLE_MESSAGE_DETAILS_EXTRACTOR =
            content -> "Vehicle id: " + content.getVehicleId();
    private static final Function<CargoMessageContent, String> CARGO_MESSAGE_DETAILS_EXTRACTOR =
            content -> "Content id: " + content.getCargoId();

    private final List<MessageDetailsConditionalExtractor<? extends MessageContent>> extractors = List.of(
            new MessageDetailsConditionalExtractor<>(VehicleMessageContent.class, VEHICLE_MESSAGE_DETAILS_EXTRACTOR),
            new MessageDetailsConditionalExtractor<>(CargoMessageContent.class, CARGO_MESSAGE_DETAILS_EXTRACTOR)
    );

    void logReceived(Message<? extends MessageContent> message)
    {
        List<String> messageDetails = new ArrayList<>();

        for (MessageDetailsConditionalExtractor<? extends MessageContent> extractor : extractors)
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

    private static class MessageDetailsConditionalExtractor<T extends MessageContent>
    {
        private final Class<T> contentType;
        private final Function<T, String> extractor;

        MessageDetailsConditionalExtractor(Class<T> contentType, Function<T, String> extractor)
        {
            this.contentType = contentType;
            this.extractor = extractor;
        }

        private String extractFrom(MessageContent content)
        {
            if (contentType.isAssignableFrom(content.getClass()))
            {
                //noinspection unchecked
                return extractor.apply((T) content);
            }
            return null;
        }
    }
}