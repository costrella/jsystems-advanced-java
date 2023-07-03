package pl.jsystems.advancedjava.answers.frompawelm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.lambdas.solutions.s7lambdasandgenerics.contents.CargoMessageContent;
import pl.jsystems.advancedjava.lambdas.solutions.s7lambdasandgenerics.contents.MessageContent;
import pl.jsystems.advancedjava.lambdas.solutions.s7lambdasandgenerics.contents.VehicleMessageContent;
import pl.jsystems.advancedjava.lambdas.solutions.s7lambdasandgenerics.message.Message;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class Solution3
{
    private static final Logger LOGGER = LoggerFactory.getLogger(Solution3.class);

    private static final BiConsumer<List<String>, VehicleMessageContent> VEHICLE_MESSAGE_LOGGER =
            (details, content) -> details.add("Vehicle id: " + content.getVehicleId());
    private static final BiConsumer<List<String>, CargoMessageContent> CARGO_MESSAGE_LOGGER =
            (details, content) -> details.add("Cargo id: " + content.getCargoId());

    private final MessageDetailsIntoListExtractors extractors = new MessageDetailsIntoListExtractors();

    Solution3() {
        extractors.registerExtractor(VehicleMessageContent.class, VEHICLE_MESSAGE_LOGGER);
        extractors.registerExtractor(CargoMessageContent.class, CARGO_MESSAGE_LOGGER);
    }

    void logReceived(Message<? extends MessageContent> message)
    {
        List<String> messageDetails = new ArrayList<>();

        extractors.extractFor(messageDetails, message.content());

        LOGGER.info("Received new message with id {} of type {}. Sent at: {}, received at {}. Details: {}.",
                message.id(),
                message.content().getClass().getSimpleName(),
                message.sentAt(),
                Instant.now(),
                messageDetails);
    }


    private static class MessageDetailsIntoListExtractors
    {
        private static final BiConsumer<List<String>, ?> DEFAULT_NOOP_CONSUMER = (list, content) -> {};
        private final Map<Class<?>, BiConsumer<List<String>, ?>> extractors = new HashMap<>();

        public <T extends MessageContent> void registerExtractor(Class<T> cls, BiConsumer<List<String>, T> extractor) {
            extractors.put(cls, extractor);
        }

        public <T extends MessageContent> void extractFor(List<String> detailsList, T content) {
            // to jest super bezpieczne, bo my kontrolujemy to co wkladamy do mapy i parametry muszą się zgadzać.
            // kompilator jednak tego 'nie wie' i informuje nas o tym, że może to co jest w mapie nie odpowiada temu
            // czego oczekujemy. Ale poniewaz wiemy co jest w mapie i czego oczekujemy - możemy zrobic:


            //noinspection unchecked
            BiConsumer<List<String>, T> consumer = (BiConsumer<List<String>, T>) extractors.getOrDefault(content.getClass(), DEFAULT_NOOP_CONSUMER);
            consumer.accept(detailsList, content);
        }
    }
}