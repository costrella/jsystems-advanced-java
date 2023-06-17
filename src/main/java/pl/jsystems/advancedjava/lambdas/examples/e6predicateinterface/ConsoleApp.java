package pl.jsystems.advancedjava.lambdas.examples.e6predicateinterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Predicate;

class ConsoleApp
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleApp.class);

    private final Consumer<String> stringConsumer;
    private final Predicate<String> endApplicationPredicate;

    ConsoleApp(Consumer<String> stringConsumer, Predicate<String> endApplicationPredicate)
    {
        this.stringConsumer = stringConsumer;
        this.endApplicationPredicate = endApplicationPredicate;
    }

    void startConsoleApp()
    {
        LOGGER.info("Starting new app.");
        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            String input = scanner.nextLine();
            if (endApplicationPredicate.test(input))
            {
                break;
            }
            stringConsumer.accept(input);
        }
        LOGGER.info("Stopping app.");
    }
}
