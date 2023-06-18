package pl.jsystems.advancedjava.streams.examples.e4foreach;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

class StreamsExample4ForEach
{
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamsExample4ForEach.class);

    public static void main(String[] args)
    {
        StreamsExample4ForEach app = new StreamsExample4ForEach();
        app.run();
    }

    private void run()
    {
        List<String> strings = List.of("1", "2", "3", "3");
        LOGGER.info("Created a list of Strings: {} - creating stream out of them", strings);

        strings.stream().forEach(input -> LOGGER.info("Element in the list: {}", input));
        LOGGER.info("Another example");
        strings.forEach(input -> LOGGER.info("Element in the list: {}", input));
        LOGGER.info("Filtered list for each");
        strings.stream().filter("3"::equals).forEach(input -> LOGGER.info("Element in the filtered list: {}", input));
    }
}
