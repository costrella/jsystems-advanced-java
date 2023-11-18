package pl.jsystems.advancedjava.lambdas.examples.e7biconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

class ConsoleApp
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleApp.class);

    private final BiConsumer<String, String> stringConsumer;
    private final Predicate<String> endApplicationPredicate;

    ConsoleApp(BiConsumer<String, String> stringConsumer, Predicate<String> endApplicationPredicate)
    {
        this.stringConsumer = stringConsumer;
        this.endApplicationPredicate = endApplicationPredicate;
    }

    void startConsoleApp()
    {
        LOGGER.info("Starting new app.");
        Scanner scanner = new Scanner(System.in);

        String previousInput;
        String input = null;
        while (true)
        {
            previousInput = input;
            input = scanner.nextLine();
            if (endApplicationPredicate.test(input))
            {
                break;
            }
            stringConsumer.accept(input, previousInput);
        }
        LOGGER.info("Stopping app.");
    }
}