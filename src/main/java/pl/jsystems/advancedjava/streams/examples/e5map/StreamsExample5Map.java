package pl.jsystems.advancedjava.streams.examples.e5map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

class StreamsExample5Map
{
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamsExample5Map.class);

    public static void main(String[] args)
    {
        StreamsExample5Map app = new StreamsExample5Map();
        app.run();
    }

    private void run()
    {
        List<String> strings = List.of("1", "2", "3", "3");
        LOGGER.info("Created a list of Strings: {} - creating stream out of them", strings);

        List<Integer> integers = strings.stream()
                .map(Integer::valueOf)
                .map(Long::valueOf)
                .map(Long::doubleValue)
                .map(Double::intValue)
                .toList();

        LOGGER.info("List of integers: {}", integers);
    }
}
