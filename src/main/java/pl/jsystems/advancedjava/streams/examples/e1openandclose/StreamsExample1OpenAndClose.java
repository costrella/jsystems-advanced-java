package pl.jsystems.advancedjava.streams.examples.e1openandclose;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class StreamsExample1OpenAndClose
{
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamsExample1OpenAndClose.class);

    public static void main(String[] args)
    {
        new StreamsExample1OpenAndClose().run();
    }

    private void run()
    {
        List<String> strings = List.of("1", "2", "3", "3");
        LOGGER.info("Created a list of Strings: {} - creating stream out of them", strings);

        Stream<String> stringStream = strings.stream();
        LOGGER.info("How does Stream.toString() look like? Result: {}", stringStream);

        // returns unmodifiableList array list.
        List<String> sameStrings = stringStream.toList();
        LOGGER.info("Collected all: {}", sameStrings);

        // This fails - by design. Streams are either open or closed. You can close it only once!.
        Set<String> setFromStream = stringStream.collect(Collectors.toSet());
        LOGGER.info("Collected all to set (or not): {}", setFromStream);
    }
}
