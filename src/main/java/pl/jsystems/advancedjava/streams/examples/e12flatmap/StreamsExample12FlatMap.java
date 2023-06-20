package pl.jsystems.advancedjava.streams.examples.e12flatmap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class StreamsExample12FlatMap
{
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamsExample12FlatMap.class);

    public static void main(String[] args)
    {
        new StreamsExample12FlatMap().run();
    }

    private void run()
    {
        List<Integer> integers = IntStream.of(1, 2, 4, 42, 4, 2, -11, 555).boxed().toList();
        LOGGER.info("List: {}", integers);
        Set<Integer> moreIntegers = IntStream.of(-1, -2, -4, 1, 2, 4, 55).boxed().collect(Collectors.toSet());
        LOGGER.info("Set: {}", moreIntegers);

        int sum = Stream.of(integers, moreIntegers)
                .peek(list -> LOGGER.info("Lists in stream: - {}", list))
                .flatMap(Collection::stream)
                .reduce(Integer::sum).orElseThrow();
        LOGGER.info("Sum: {}", sum);

        sum = Stream.of(integers.stream(), moreIntegers.stream())
                .peek(list -> LOGGER.info("Streams in stream: - {}", list))
                .flatMap(Function.identity())
                .reduce(Integer::sum).orElseThrow();
        LOGGER.info("Sum: {}", sum);

        List<BigDecimal> bigDecimals = IntStream.range(40, 50).mapToObj(BigDecimal::new).toList();
        LOGGER.info("Some Big decimals: {}", bigDecimals);
    }
}
