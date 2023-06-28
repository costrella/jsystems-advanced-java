package pl.jsystems.advancedjava.reflection.solutions.s3buildstuff;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.reflection.solutions.s3buildstuff.contents.CargoMessageContent;
import pl.jsystems.advancedjava.reflection.solutions.s3buildstuff.contents.MessageContent;
import pl.jsystems.advancedjava.reflection.solutions.s3buildstuff.contents.VehicleMessageContent;
import pl.jsystems.advancedjava.reflection.solutions.s3buildstuff.message.Message;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

@ForDependencyInjection
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

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    void logReceived(Message<? extends MessageContent> message)
    {
        int currentCounter = atomicInteger.incrementAndGet();
        List<String> messageDetails = new ArrayList<>();

        for (MessageDetailsConditionalExtractor<? extends MessageContent> extractor : extractors)
        {
            String details = extractor.extractFrom(message.content());
            if (details != null)
            {
                messageDetails.add(details);
            }
        }

        LOGGER.info("Received new ({}) message with id {} of type {}. Sent at: {}, received at {}. Details: {}.",
                currentCounter,
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
