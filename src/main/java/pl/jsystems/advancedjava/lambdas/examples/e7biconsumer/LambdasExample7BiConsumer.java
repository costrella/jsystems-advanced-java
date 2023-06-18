package pl.jsystems.advancedjava.lambdas.examples.e7biconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

class LambdasExample7BiConsumer
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LambdasExample7BiConsumer.class);

    public static void main(String[] args)
    {
        PromptPrinter printer = new PromptPrinter();

        // or better - BiConsumer<String, String> biConsumer = printer::displayPromptFor;
        BiConsumer<String, String> biConsumer = (input, previousInput) ->
        {
            printer.displayPromptFor(input, previousInput);
        };
        new ConsoleApp(biConsumer, "q"::equals).startConsoleApp();
        // or better
        new ConsoleApp(printer::displayPromptFor, "quit"::equals).startConsoleApp();
    }

    private static class PromptPrinter
    {
        void displayPromptFor(String input, String previousInput)
        {
            if (previousInput != null)
            {
                LOGGER.info("Last time you've put: {}", previousInput);
            }
            LOGGER.info("Now you've said: {}", input);
            LOGGER.info("What else will you tell me?");
        }
    }
}
