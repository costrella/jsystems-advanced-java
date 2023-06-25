package pl.jsystems.advancedjava.lambdas.examples.e10supplierinterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Supplier;

class ConsoleApp
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleApp.class);

    private final BiFunction<String, String, Boolean> inputBiFunction;
    private final Supplier<String> startTextSupplier;
    private final Supplier<String> endTextSupplier;

    ConsoleApp(BiFunction<String, String, Boolean> inputBiFunction,
               Supplier<String> startTextSupplier,
               Supplier<String> endTextSupplier)
    {
        this.inputBiFunction = inputBiFunction;
        this.startTextSupplier = startTextSupplier;
        this.endTextSupplier = endTextSupplier;
    }

    void startConsoleApp()
    {
        LOGGER.info(startTextSupplier.get());
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
        LOGGER.info(endTextSupplier.get());
    }
}
