package pl.jsystems.advancedjava.streams.examples.e3basicfilters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Predicate;

class StreamsExample3BasicFilters
{
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamsExample3BasicFilters.class);

    public static void main(String[] args)
    {
        StreamsExample3BasicFilters app = new StreamsExample3BasicFilters();
        app.run();

        String elementsToCount = "3";
        Long count = app.countElementsInList(elementsToCount::equals);
        LOGGER.info("There are {} instances of {} in the list", count, elementsToCount);

        elementsToCount = "2";
        count = app.countElementsInList(elementsToCount::equals);
        LOGGER.info("There are {} instances of {} in the list", count, elementsToCount);

        elementsToCount = "1";
        count = app.countElementsInList(elementsToCount::equals);
        LOGGER.info("There are {} instances of {} in the list", count, elementsToCount);
    }

    private void run()
    {
        List<String> strings = List.of("1", "2", "3", "3");
        LOGGER.info("Created a list of Strings: {} - creating stream out of them", strings);

        Long countOfThrees = strings.stream().filter(input -> "3".equals(input)).count();
        LOGGER.info("Count of threes in this string: {}", countOfThrees);
    }

    private Long countElementsInList(Predicate<String> predicate)
    {
        List<String> strings = List.of("1", "2", "3", "3");
        LOGGER.info("Created a list of Strings: {} - creating stream out of them", strings);

        return strings.stream().filter(predicate).count();
    }
}
