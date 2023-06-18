package pl.jsystems.advancedjava.lambdas.examples.e9bifunctioninterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

class LambdasExample9BiFunctionInterface
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LambdasExample9BiFunctionInterface.class);

    public static void main(String[] args)
    {
        PromptPrinter printer = new PromptPrinter();

        new ConsoleApp(printer::displayPromptFor).startConsoleApp();
    }

    private static class PromptPrinter
    {
        boolean displayPromptFor(String input, String previousInput)
        {
            if (Objects.equals(input, previousInput))
            {
                LOGGER.info("You're really boring...");
                return false;
            }
            if (previousInput != null)
            {
                LOGGER.info("Last time you've put: {}", previousInput);
            }
            LOGGER.info("Now you've said: {}", input);
            LOGGER.info("What else will you tell me?");
            return true;
        }
    }
}
