package pl.jsystems.advancedjava.streams.examples.e16generatorsandlimits;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

class StreamsExample16GeneratorsAndLimits
{
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamsExample16GeneratorsAndLimits.class);
    private static int supplierCounter = 0;

    public static void main(String[] args)
    {
        new StreamsExample16GeneratorsAndLimits().run();
    }

    private void run()
    {
        LOGGER.info("More streams");
        LOGGER.info("Builder...");
        Stream<Integer> streamOfTwoIntegers = Stream.<Integer>builder().add(1).add(2).build();
        streamOfTwoIntegers.forEach(i -> LOGGER.info("Next int: {}", i));

        LOGGER.info("Iterator...");
        Stream<Integer> streamOfIntegers = Stream.iterate(1, i -> i + 1).limit(20);
        streamOfIntegers.forEach(i -> LOGGER.info("Next int: {}", i));

        LOGGER.info("Iterator with condition...");
        Stream<Integer> otherStreamOfIntegers = Stream.iterate(1, i -> i < 5, i -> i + 1);
        otherStreamOfIntegers.forEach(i -> LOGGER.info("Next int: {}", i));

        LOGGER.info("Generator - supplier...");
        Stream<Integer> generatedStreamOfIntegers = Stream.generate(this::getNextInteger).limit(10);
        generatedStreamOfIntegers.forEach(i -> LOGGER.info("Next int: {}", i));

        LOGGER.info("Generator - supplier without limit...");
        Stream<Integer> generatedStreamOfIntegersNoLimits = Stream.generate(this::getNextInteger).map(this::simpleIdentityFunction);
        LOGGER.info("Nothing is happening yet, stream is not progressing... Calling findFirst now...");
        generatedStreamOfIntegersNoLimits.findFirst().ifPresent(i -> LOGGER.info("Found first integer: {}", i));
    }

    private Integer getNextInteger()
    {
        LOGGER.info("==========================");
        LOGGER.info("Generating integer: " + ++supplierCounter);
        return supplierCounter;
    }

    private <T> T simpleIdentityFunction(T input)
    {
        LOGGER.info("Simple identity mapping for: {}", input);
        return input;
    }
}
