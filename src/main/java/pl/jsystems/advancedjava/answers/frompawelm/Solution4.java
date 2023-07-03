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
import java.util.function.Consumer;
import java.util.function.Function;

public class Solution4
{
    private static final Logger LOGGER = LoggerFactory.getLogger(Solution4.class);

    private static final Function<VehicleMessageContent, String> VEHICLE_MESSAGE_LOGGER =
            (content) -> "Vehicle id: " + content.getVehicleId();
    private static final Function<CargoMessageContent, String> CARGO_MESSAGE_LOGGER =
            (content) -> "Cargo id: " + content.getCargoId();

    private final MessageDetailsIntoListExtractors extractors = new MessageDetailsIntoListExtractors();

    Solution4() {
        extractors.registerExtractor(VehicleMessageContent.class, VEHICLE_MESSAGE_LOGGER);
        extractors.registerExtractor(CargoMessageContent.class, CARGO_MESSAGE_LOGGER);
    }

    void logReceived(Message<? extends MessageContent> message)
    {
          LOGGER.info("Received new message with id {} of type {}. Sent at: {}, received at {}. Details: {}.",
                message.id(),
                message.content().getClass().getSimpleName(),
                message.sentAt(),
                Instant.now(),
                extractors.extractFor(message.content()));
    }

    // teraz klasa MessageLogger jest rozszerzalna!
    public <T extends MessageContent> void addExtractorFor(Class<T> cls, Function<T, String> extractor) {
        extractors.registerExtractor(cls, extractor);
    }


    private static class MessageDetailsIntoListExtractors
    {
        private static final Function<? extends MessageContent, String> DEFAULT_NOOP_FUNCTION = (content) -> "";
        private final Map<Class<? extends MessageContent>, Function<? extends MessageContent, String>> extractors = new HashMap<>();

        public <T extends MessageContent> void registerExtractor(Class<T> cls, Function<T, String> extractor) {
            extractors.put(cls, extractor);
        }

        public <T extends MessageContent> String extractFor(T content) {
            // to jest super bezpieczne, bo my kontrolujemy to co wkladamy do mapy i parametry muszą się zgadzać.
            // kompilator jednak tego 'nie wie' i informuje nas o tym, że może to co jest w mapie nie odpowiada temu
            // czego oczekujemy. Ale poniewaz wiemy co jest w mapie i czego oczekujemy - możemy zrobic:


            //noinspection unchecked
            Function<T, String> extractor = (Function<T, String>) extractors.getOrDefault(content.getClass(), DEFAULT_NOOP_FUNCTION);
            return extractor.apply(content);
        }
    }
}