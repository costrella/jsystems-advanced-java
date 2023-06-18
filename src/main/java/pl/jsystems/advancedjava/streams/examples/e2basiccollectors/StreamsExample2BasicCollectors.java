package pl.jsystems.advancedjava.streams.examples.e2basiccollectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class StreamsExample2BasicCollectors
{
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamsExample2BasicCollectors.class);

    public static void main(String[] args)
    {
        new StreamsExample2BasicCollectors().run();
    }

    private void run()
    {
        List<String> strings = List.of("1", "2", "3", "3");
        LOGGER.info("Created a list of Strings: {} - creating stream out of them", strings);

        List<String> sameStrings = strings.stream().collect(Collectors.toList());
        LOGGER.info("Put through stream and collected all to list: {}", sameStrings);

        List<String> sameStrings2 = strings.stream().toList();
        LOGGER.info("Put through stream and collected all to list: {}", sameStrings2);

        Set<String> stringsInSet = strings.stream().collect(Collectors.toSet());
        LOGGER.info("Collected all to set: {}, original list is still the same: {}", stringsInSet, strings);

        Long countInStrings = stringsInSet.stream().collect(Collectors.counting());
        LOGGER.info("Number of strings in list: {}", countInStrings);

        Long countInSet = stringsInSet.stream().count();
        LOGGER.info("Number of strings in set: {}", countInSet);

        String joinedStrings = stringsInSet.stream().collect(Collectors.joining("-"));
        LOGGER.info("Strings joined by {}: {}", "'-'", joinedStrings);
    }
}
