package pl.jsystems.advancedjava.lambdas.examples.e11typedlambdaparameters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

class LambdasExample11TypedLambdaParameters
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LambdasExample11TypedLambdaParameters.class);

    public static void main(String[] args)
    {
        PromptPrinter printer = new PromptPrinter();
        Instant startTime = Instant.now();

        Supplier<String> endTextSupplier = () -> {
            long timePassed = Instant.now().toEpochMilli() - startTime.toEpochMilli();
            timePassed = timePassed / 1000;
            return "Ending! We've talked for: " + timePassed + " seconds!";
        };

        new ConsoleApp
                (
                        // we can add types to parameters, but its redundant.
                        (String input, String previousInput) -> printer.displayPromptFor(input, previousInput),
                        () -> "Staring!",
                        endTextSupplier
                ).startConsoleApp();
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
