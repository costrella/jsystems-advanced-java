package pl.jsystems.advancedjava.streams.examples.e9minmaxpeak;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class StreamsExample9MinMaxPeek
{
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamsExample9MinMaxPeek.class);

    public static void main(String[] args)
    {
        new StreamsExample9MinMaxPeek().run();
    }

    private void run()
    {
        List<Integer> integers = getIntegers();
        LOGGER.info("Original list: {}", integers);

        Optional<Integer> result = integers.stream().max(Integer::compareTo);
        result.ifPresent(max -> LOGGER.info("Max value: {}", max));

        result = integers.stream().min(Integer::compareTo);
        result.ifPresent(min -> LOGGER.info("Max value: {}", min));

        result = integers.stream().collect(Collectors.maxBy(Integer::compareTo));
        result.ifPresent(max -> LOGGER.info("Max value: {}", max));

        result = integers.stream().collect(Collectors.minBy(Integer::compareTo));
        result.ifPresent(min -> LOGGER.info("Max value: {}", min));

        long count = integers.stream().peek(someIntAlongItsWay -> LOGGER.info("Number being processed by peek1 {}", someIntAlongItsWay)).count();
        result.ifPresent(min -> LOGGER.info("Count is {}, but no logs from 'peek' shown above - why?", count));

        List<String> mapped = integers.stream().peek(someIntAlongItsWay -> LOGGER.info("Number being processed by peek2{}", someIntAlongItsWay))
                .map(Object::toString)
                .toList();
        result.ifPresent(min -> LOGGER.info("Number of ints: {}", mapped.size()));
    }

    private static List<Integer> getIntegers()
    {
        return List.of(1, 2, 4, 42, 4, 2, 1);
    }
}
