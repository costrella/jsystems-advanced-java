package pl.jsystems.advancedjava.lambdas.examples.e8functioninterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class LambdasExample8FunctionInterface
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LambdasExample8FunctionInterface.class);

    public static void main(String[] args)
    {
        PromptPrinter printer = new PromptPrinter();

        new ConsoleApp(printer::displayPromptFor).startConsoleApp();
    }

    private static class PromptPrinter
    {
        boolean displayPromptFor(String input)
        {
            if ("bye".equals(input))
            {
                LOGGER.info("Bye bye!");
                return false;
            }
            LOGGER.info("Now you've said: {}", input);
            LOGGER.info("What else will you tell me?");
            return true;
        }
    }
}
