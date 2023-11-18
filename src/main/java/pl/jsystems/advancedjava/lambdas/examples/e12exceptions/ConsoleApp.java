package pl.jsystems.advancedjava.lambdas.examples.e12exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Supplier;

class ConsoleApp
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleApp.class);

    private final BiFunction<String, String, Boolean> inputBiFunction;
    private final LambdasExample12Exceptions.MyOwnSupplier<String> startTextSupplier;
    private final Supplier<String> endTextSupplier;

    ConsoleApp(BiFunction<String, String, Boolean> inputBiFunction,
               LambdasExample12Exceptions.MyOwnSupplier<String> startTextSupplier,
               Supplier<String> endTextSupplier)
    {
        this.inputBiFunction = inputBiFunction;
        this.startTextSupplier = startTextSupplier;
        this.endTextSupplier = endTextSupplier;
    }

    void startConsoleApp()
    {
        try
        {
            LOGGER.info(startTextSupplier.get());
        } catch (Exception e)
        {
            LOGGER.info("how unlucky! " + e.getMessage());
            return;
        }
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
        try
        {
            LOGGER.info(endTextSupplier.get());
        } catch (IllegalStateException e)
        {
            LOGGER.error("You made your computer angry! It says: {}", e.getMessage());
            LOGGER.warn("You better apologise!");
            while (!"I'm so sorry!!!".equals(input))
            {
                input = scanner.nextLine();
            }
            LOGGER.info("There you go - that's much better!");
        }
    }
}