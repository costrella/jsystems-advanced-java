package pl.jsystems.advancedjava.streams.examples.e11findandmatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

class StreamsExample11FindAndMatch
{
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamsExample11FindAndMatch.class);

    public static void main(String[] args)
    {
        new StreamsExample11FindAndMatch().run();
    }

    private void run()
    {
        List<Integer> integers = List.of(1, 2, 4, 42, 4, 2, -11, 555);

        LOGGER.info("Original list1: {}", integers);
        Set<Integer> moreIntegers = Set.of(-1, -2, -4, 1, 2, 4, 55);
        LOGGER.info("Original list2: {}", moreIntegers);

        // try running multiple times
        moreIntegers.stream().findAny().ifPresent(output -> LOGGER.info("Find any: {}", output));
        moreIntegers.stream().sorted().findAny().ifPresent(output -> LOGGER.info("Find any sorted: {}", output));
        integers.stream().findFirst().ifPresent(output -> LOGGER.info("Find first: {}", output));
        integers.stream().sorted().findFirst().ifPresent(output -> LOGGER.info("Find first: sorted {}", output));

        boolean containsNegative = integers.stream().anyMatch(number -> number < 0);
        LOGGER.info("Integer lists contains negative numbers: {}", containsNegative);

        boolean containsOverOneHundred = integers.stream().noneMatch(number -> number > 99);
        LOGGER.info("Integer lists contains one hundred: {}", containsOverOneHundred);

        boolean onlyBetween50s = integers.stream().allMatch(number -> number > -50 & number < 50);
        LOGGER.info("Integer lists contains numbers between -50 to 50: {}", onlyBetween50s);

        //Stream generators:
        LOGGER.info("{}", IntStream.range(0, 10).anyMatch(value -> value == 0));
        LOGGER.info("{}", IntStream.range(0, 22).allMatch(value -> value >= 1));
        LOGGER.info("{}", LongStream.range(0L, 22L).allMatch(value -> value >= 0L));
    }
}