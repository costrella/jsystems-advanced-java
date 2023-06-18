package pl.jsystems.advancedjava.lambdas.exercises.e10lambdasincollections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class LambdasExercise10LambdasInCollections
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LambdasExercise10LambdasInCollections.class);

    public static void main(String[] args)
    {
        // todo stuff
        new ConsoleApp().startConsoleApp();
    }

    private static class PromptPrinter
    {
        void displayPromptFor(String input)
        {
            LOGGER.info("Input: {}", input);
            LOGGER.info("What else?");
        }
    }

    private static class AuditLogger
    {
        void log(String input)
        {
            LOGGER.info("Logging {} to audit db...", input);
        }
    }
}
