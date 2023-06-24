package pl.jsystems.advancedjava.streams.examples.e18exceptionhandling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class StreamsExample18ExceptionHandling
{
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamsExample18ExceptionHandling.class);
    private static int supplierCounter = 0;
    private static int mappingCounter = 0;

    public static void main(String[] args)
    {
        new StreamsExample18ExceptionHandling().run();
    }

    private void run()
    {
        LOGGER.info("Oups, what now?!");

        supplierCounter = 0;
        mappingCounter = 0;
        LOGGER.info("==========================");
        LOGGER.info("==========================");
        LOGGER.info("Collect all");
        List<Integer> failedIntegers = new ArrayList<>();
        Set<Integer> allInts = Stream.generate(this::getNextInteger).limit(10)
                .map(this::simpleIdentityFunction)
                .map(i ->
                {
                    try
                    {
                        return mappingSomethingThrowingExceptions(i);
                    } catch (Exception e)
                    {
                        LOGGER.info("Exception caught! {} for int: {} ", e.getMessage(), i);
                        failedIntegers.add(i);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .map(this::otherIdentityFunction)
                .collect(Collectors.toSet());
        LOGGER.info("Not going to get here... Or am I? Result: {}", allInts);
        LOGGER.info("Failed integers.");

        supplierCounter = 0;
        mappingCounter = 0;
        LOGGER.info("==========================");
        LOGGER.info("==========================");
        LOGGER.info("Collect all");
        allInts = Stream.generate(this::getNextInteger).limit(10)
                .map(this::simpleIdentityFunction)
                .map(this::mapIntHandlingExceptions)
                .filter(Objects::nonNull)
                .map(this::otherIdentityFunction)
                .collect(Collectors.toSet());
        LOGGER.info("Not going to get here... Or am I? Result: {}", allInts);

        supplierCounter = 0;
        mappingCounter = 0;
        LOGGER.info("==========================");
        LOGGER.info("==========================");
        LOGGER.info("Collect all");
        allInts = Stream.generate(this::getNextInteger).limit(10)
                .map(this::simpleIdentityFunction)
                .map(t -> mapHandlingExceptions(this::mappingSomethingThrowingExceptions, t))
                .filter(Objects::nonNull)
                .map(this::otherIdentityFunction)
                .collect(Collectors.toSet());
        LOGGER.info("Not going to get here... Or am I? Result: {}", allInts);

        supplierCounter = 0;
        mappingCounter = 0;
        LOGGER.info("==========================");
        LOGGER.info("==========================");
        LOGGER.info("Collect all - throwing runtime exception - this time we'll fail!");
        allInts = Stream.generate(this::getNextInteger).limit(10)
                .map(this::simpleIdentityFunction)
                .map(i ->
                {
                    try
                    {
                        return mappingSomethingThrowingExceptions(i);
                    } catch (Exception e)
                    {
                        throw new RuntimeException(e);
                    }
                })
                .filter(Objects::nonNull)
                .map(this::otherIdentityFunction)
                .collect(Collectors.toSet());
        LOGGER.info("Not going to get here... Or am I? Result: {}", allInts);
    }

    private Integer mapIntHandlingExceptions(final Integer i)
    {
        try
        {
            return mappingSomethingThrowingExceptions(i);
        } catch (Exception e)
        {
            LOGGER.info("Exception caught! {} for int: {} ", e.getMessage(), i);
            return null;
        }
    }

    private <T> T mapHandlingExceptions(MyBiFunction<T> functionThrowingExceptions, T input)
    {
        try
        {
            return functionThrowingExceptions.apply(input);
        } catch (Exception e)
        {
            LOGGER.info("Exception caught! {} for int: {} ", e.getMessage(), input);
            return null;
        }
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

    private <T> T otherIdentityFunction(T input)
    {
        LOGGER.info("Other identity mapping for: {}", input);
        return input;
    }

    private <T> T mappingSomethingThrowingExceptions(T input) throws Exception
    {
        LOGGER.info("Mapping with exceptions: {}", input);
        if (++mappingCounter > 5)
        {
            throw new Exception("Cannot map this: " + input);
        }
        return input;
    }

    @FunctionalInterface
    private interface MyBiFunction<T>
    {
        T apply(T input) throws Exception;
    }
}
