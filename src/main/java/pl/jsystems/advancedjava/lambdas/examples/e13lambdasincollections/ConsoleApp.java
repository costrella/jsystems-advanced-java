package pl.jsystems.advancedjava.lambdas.examples.e13lambdasincollections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

class ConsoleApp
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleApp.class);

    private final List<Consumer<String>> inputConsumers;

    ConsoleApp(List<Consumer<String>> inputConsumers)
    {
        this.inputConsumers = inputConsumers;
    }

    void startConsoleApp()
    {
        LOGGER.info("Starting new app.");
        Scanner scanner = new Scanner(System.in);

        String input = null;
        while (!"q".equals(input))
        {
            input = scanner.nextLine();
            for (Consumer<String> inputConsumer : inputConsumers)
            {
                inputConsumer.accept(input);
            }
        }
        LOGGER.info("Stopping app.");
    }
}