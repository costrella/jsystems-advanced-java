package pl.jsystems.advancedjava.lambdas.solutions.s10lambdasincollections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

class ConsoleApp
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleApp.class);

    private final Map<String, LambdasExercise10LambdasInCollections.InputHandler<Integer>> inputHandlers;

    ConsoleApp(Map<String, LambdasExercise10LambdasInCollections.InputHandler<Integer>> inputHandlers)
    {
        this.inputHandlers = inputHandlers;
    }

    void startConsoleApp()
    {
        LOGGER.info("Starting new app.");
        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            String input = scanner.nextLine();
            try
            {
                Integer result = inputHandlers
                        .getOrDefault(input, () ->
                        {
                            LOGGER.info("Unknown input!");
                            return -1;
                        })
                        .handle();
                if (result == 0)
                {
                    break;
                }
            } catch (IOException e)
            {
                LOGGER.error("Something wrong happened. Someone entered 42!", e);
                throw new RuntimeException(e);
            }
        }
        LOGGER.info("Stopping app.");
    }
}

