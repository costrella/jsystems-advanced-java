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
import java.util.function.Predicate;

public class Solution2
{
    private static final Logger LOGGER = LoggerFactory.getLogger(Solution2.class);

    private static final Predicate<VehicleMessageContent> IS_VEHICLE_MESSAGE_CONTENT = content -> content instanceof VehicleMessageContent;
    private static final Predicate<CargoMessageContent> IS_CARGO_MESSAGE_CONTENT = content -> content instanceof CargoMessageContent;

    private static final BiConsumer<List<String>, VehicleMessageContent> VEHICLE_MESSAGE_LOGGER =
            (details, content) -> details.add("Vehicle id: " + content.getVehicleId());
    private static final BiConsumer<List<String>, CargoMessageContent> CARGO_MESSAGE_LOGGER =
            (details, content) -> details.add("Cargo id: " + content.getCargoId());

    private final MessageDetailsExtractors extractors = new MessageDetailsExtractors();

    Solution2() {
        extractors.registerExtractor(IS_VEHICLE_MESSAGE_CONTENT, VEHICLE_MESSAGE_LOGGER);
        extractors.registerExtractor(IS_CARGO_MESSAGE_CONTENT, CARGO_MESSAGE_LOGGER);
    }

    void logReceived(Message<? extends MessageContent> message)
    {
        List<String> messageDetails = new ArrayList<>();

        extractors.extractInto(messageDetails, message.content());

        LOGGER.info("Received new message with id {} of type {}. Sent at: {}, received at {}. Details: {}.",
                message.id(),
                message.content().getClass().getSimpleName(),
                message.sentAt(),
                Instant.now(),
                messageDetails);
    }

    private static class MessageDetailsExtractors
    {
        private static final Predicate<? extends MessageContent> DEFAULT_PREDICATE = cls -> false;
        private static final BiConsumer<List<String>, ? extends MessageContent> DEFAULT_EXTRACTOR = (list, content) -> {};
        private final Map<Class<?>, Predicate<? extends MessageContent>> predicates = new HashMap<>();
        private final Map<Class<?>, BiConsumer<List<String>, ? extends MessageContent>> extractors = new HashMap<>();

        <T extends MessageContent> void registerExtractor(Predicate<T> canExtractFrom, BiConsumer<List<String>, T> extractor)
        {
            canExtractFrom.getClass().getGenericSuperclass().getTypeName();
            Class<T> predicateMatchingClass = getGenericClassForPredicate(canExtractFrom);
            predicates.put(predicateMatchingClass, canExtractFrom);
            extractors.put(predicateMatchingClass, extractor);
        }

        private <T extends MessageContent> void extractInto(List<String> messageDetails, T content)
        {
            // to jest bezpieczne, bo wiemy, ze gdy pobierzemy predykat po klasie, otrzymamy ten który odpowiada danej
            // treści - a to dzięki temu, że w klasie 'registerExtractor' porównujemy typy 'R' predicate i extractora
            // (domyslny ekstraktor tez moze byc bezpiecznie zmapowany),

            //noinspection unchecked
            if (((Predicate<T>) predicates.getOrDefault(content.getClass(), DEFAULT_PREDICATE)).test(content))
            {
                // to też jest bezpieczne - wiemy, że pobierzemy odpowiedni konsumer lub ten domyślny.

                //noinspection unchecked
                BiConsumer<List<String>, T> extractor = (BiConsumer<List<String>, T>) extractors.getOrDefault(content.getClass(), DEFAULT_EXTRACTOR);
                extractor.accept(messageDetails, content);
            }
        }

        private static <R extends MessageContent> Class<R> getGenericClassForPredicate(Predicate<R> canExtractFrom)
        {
            try
            {
                // to jest bezpieczne - z refleksji dostajemy typ parametru generycznego i zwracamy klase dla tego typu
                // kompilator jednak tego nie wie - wiec nas ostrzega, stąd komentarz niżej:

                //noinspection unchecked
                return (Class<R>) Class.forName(canExtractFrom.getClass().toGenericString().split("class ")[1]);
            } catch (ClassNotFoundException e)
            {
                LOGGER.error("Class for name {} not found - this should not have happened:", canExtractFrom.getClass().toGenericString());
                throw new RuntimeException(e);
            }
        }
    }
}