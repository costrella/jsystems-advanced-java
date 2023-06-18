package pl.jsystems.advancedjava.lambdas.solutions.s10lambdasincollections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class LambdasExercise10LambdasInCollections
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LambdasExercise10LambdasInCollections.class);

    public static void main(String[] args)
    {
        Map<String, InputHandler<Integer>> inputHandlers = new HashMap<>();
        inputHandlers.put("quit", () -> 0);
        inputHandlers.put("1", () -> { LOGGER.info("Entered 1"); return 1;});
        inputHandlers.put("2", () -> { LOGGER.info("Entered 2"); return 2;});
        inputHandlers.put("3", () -> { LOGGER.info("Entered 3"); return 3;});
        inputHandlers.put("4", () -> { LOGGER.info("Entered 4"); return 4;});
        inputHandlers.put("42", () -> { LOGGER.info("Entered 42 - Ups!"); throw new IOException("Ups!");});
        inputHandlers.put("4242", () -> { LOGGER.info("Entered 4242 - Ups!"); throw new RuntimeException("Ups!");});

        new ConsoleApp(inputHandlers).startConsoleApp();
    }

    interface InputHandler<T>
    {
        T handle() throws IOException;
    }
}
