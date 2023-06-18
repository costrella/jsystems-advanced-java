package pl.jsystems.advancedjava.lambdas.examples.e8functioninterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
import java.util.function.Function;

class ConsoleApp
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleApp.class);

    private final Function<String, Boolean> inputFunction;

    ConsoleApp(Function<String, Boolean> inputFunction)
    {
        this.inputFunction = inputFunction;
    }

    void startConsoleApp()
    {
        LOGGER.info("Starting new app.");
        Scanner scanner = new Scanner(System.in);

        boolean shouldContinue = true;
        while (shouldContinue)
        {
            String input = scanner.nextLine();
            shouldContinue = inputFunction.apply(input);
        }
        LOGGER.info("Stopping app.");
    }
}
