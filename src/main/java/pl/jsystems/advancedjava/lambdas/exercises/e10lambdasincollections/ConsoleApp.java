package pl.jsystems.advancedjava.lambdas.exercises.e10lambdasincollections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

class ConsoleApp
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleApp.class);


    ConsoleApp()
    {

    }

    void startConsoleApp()
    {
        LOGGER.info("Starting new app.");
        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            String input = scanner.nextLine();
            // todo stuff
            break;
        }
        LOGGER.info("Stopping app.");
    }
}