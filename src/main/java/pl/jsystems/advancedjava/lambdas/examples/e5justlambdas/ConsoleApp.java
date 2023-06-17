package pl.jsystems.advancedjava.lambdas.examples.e5justlambdas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
import java.util.function.Consumer;

class ConsoleApp
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleApp.class);

    private final Consumer<String> stringConsumer;

    ConsoleApp(Consumer<String> stringConsumer)
    {
        this.stringConsumer = stringConsumer;
    }

    void startConsoleApp()
    {
        LOGGER.info("Starting new app.");
        Scanner scanner = new Scanner(System.in);

        String input = null;
        while (!"q".equals(input))
        {
            input = scanner.nextLine();
            stringConsumer.accept(input);
        }
        LOGGER.info("Stopping app.");
    }
}
