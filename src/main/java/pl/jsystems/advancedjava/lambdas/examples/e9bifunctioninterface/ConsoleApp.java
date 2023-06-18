package pl.jsystems.advancedjava.lambdas.examples.e9bifunctioninterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
import java.util.function.BiFunction;

class ConsoleApp
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleApp.class);

    private final BiFunction<String, String, Boolean> inputBiFunction;

    ConsoleApp(BiFunction<String, String, Boolean> inputBiFunction)
    {
        this.inputBiFunction = inputBiFunction;
    }

    void startConsoleApp()
    {
        LOGGER.info("Starting new app.");
        Scanner scanner = new Scanner(System.in);

        String previousInput;
        String input = null;
        boolean shouldContinue = true;
        while (shouldContinue)
        {
            previousInput = input;
            input = scanner.nextLine();
            shouldContinue = inputBiFunction.apply(input, previousInput);
        }
        LOGGER.info("Stopping app.");
    }
}
