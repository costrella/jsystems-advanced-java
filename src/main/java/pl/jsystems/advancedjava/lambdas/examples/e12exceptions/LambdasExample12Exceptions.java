package pl.jsystems.advancedjava.lambdas.examples.e12exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.Objects;
import java.util.function.Supplier;

class LambdasExample12Exceptions
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LambdasExample12Exceptions.class);

    public static void main(String[] args)
    {
        PromptPrinter printer = new PromptPrinter();
        Instant startTime = Instant.now();

        MyOwnSupplier<String> startTextSupplier = () ->
        {
            if (Instant.now().toEpochMilli() % 2L != 0)
            {
                throw new Exception("We can only start at even milliseconds epoch time!");
            }
            return "Let's start!";
        };

        Supplier<String> endTextSupplier = () ->
        {
            long timePassed = Instant.now().toEpochMilli() - startTime.toEpochMilli();
            timePassed = timePassed / 1000;
            if (timePassed < 10)
            {
                // we cannot throw checked exceptions since Supplier interface does not have them in 'get' method definition.
                // throw new Exception("You should play for at least 10s!");
                throw new IllegalStateException("You should play for at least 10s!");
            }
            return "Ending! We've talked for: " + timePassed + " seconds!";
        };

        new ConsoleApp(printer::displayPromptFor, startTextSupplier, endTextSupplier).startConsoleApp();
    }

    interface MyOwnSupplier<T>
    {
        T get() throws Exception;
    }

    private static class PromptPrinter
    {
        boolean displayPromptFor(String input, String previousInput)
        {
            if (Objects.equals(input, previousInput))
            {
                LOGGER.info("You're really boring...");
                return false;
            }
            if (previousInput != null)
            {
                LOGGER.info("Last time you've put: {}", previousInput);
            }
            LOGGER.info("Now you've said: {}", input);
            LOGGER.info("What else will you tell me?");
            return true;
        }
    }
}