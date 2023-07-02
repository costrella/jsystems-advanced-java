package pl.jsystems.advancedjava.answers.frompawelm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.lambdas.solutions.s7lambdasandgenerics.contents.CargoMessageContent;
import pl.jsystems.advancedjava.lambdas.solutions.s7lambdasandgenerics.contents.MessageContent;
import pl.jsystems.advancedjava.lambdas.solutions.s7lambdasandgenerics.contents.VehicleMessageContent;
import pl.jsystems.advancedjava.lambdas.solutions.s7lambdasandgenerics.message.Message;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class Solution1
{
    private static final Logger LOGGER = LoggerFactory.getLogger(Solution1.class);

    private static final Predicate<MessageContent> IS_VEHICLE_MESSAGE_CONTENT = content -> content instanceof VehicleMessageContent;
    private static final Predicate<MessageContent> IS_CARGO_MESSAGE_CONTENT = content -> content instanceof CargoMessageContent;

    private static final BiConsumer<List<String>, VehicleMessageContent> VEHICLE_MESSAGE_LOGGER =
            (details, content) -> details.add("Vehicle id: " + content.getVehicleId());
    private static final BiConsumer<List<String>, CargoMessageContent> CARGO_MESSAGE_LOGGER =
            (details, content) -> details.add("Vehicle id: " + content.getCargoId());

    private final List<MessageDetailsExtractor<? extends MessageContent>> extractors = List.of(
            new MessageDetailsExtractor<>(IS_VEHICLE_MESSAGE_CONTENT, VEHICLE_MESSAGE_LOGGER),
            new MessageDetailsExtractor<>(IS_CARGO_MESSAGE_CONTENT, CARGO_MESSAGE_LOGGER)
    );

    void logReceived(Message<? extends MessageContent> message)
    {
        List<String> messageDetails = new ArrayList<>();

        extractors.forEach(messageContentMessageDetailsExtractor -> messageContentMessageDetailsExtractor.extractInto(messageDetails, message.content()));

        LOGGER.info("Received new message with id {} of type {}. Sent at: {}, received at {}. Details: {}.",
                message.id(),
                message.content().getClass().getSimpleName(),
                message.sentAt(),
                Instant.now(),
                messageDetails);
    }

    private static class MessageDetailsExtractor<R extends MessageContent>
    {
        private final Predicate<MessageContent> isKnownContent;
        private final BiConsumer<List<String>, R> extractor;

        MessageDetailsExtractor(Predicate<MessageContent> isKnownContent, BiConsumer<List<String>, R> extractor)
        {
            this.isKnownContent = isKnownContent;
            this.extractor = extractor;
        }

        private void extractInto(List<String> messageDetails, MessageContent content)
        {
            if (isKnownContent.test(content))
            {
                // tego nie przeskoczymy, ale to jest bezpieczne - widac to golym okiem.
                // O ile przekazemy do konstuktora pasujacy predykat i bikonsumer, to ta linika jest bezpieczna
                // a jak zabezpieczyć się przed pomyłką w wywołaniach konstruktora?
                extractor.accept(messageDetails, (R) content);
            }
        }
    }
}