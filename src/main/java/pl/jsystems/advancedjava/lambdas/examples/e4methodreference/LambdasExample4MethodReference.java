package pl.jsystems.advancedjava.lambdas.examples.e4methodreference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class LambdasExample4MethodReference
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LambdasExample4MethodReference.class);

    public static void main(String[] args)
    {
        PromptPrinter printer = new PromptPrinter();
        AuditLogger auditLogger = new AuditLogger();

        new ConsoleApp(input ->
        {
            printer.displayPromptFor(input);
            auditLogger.log(input);
        }).startConsoleApp();

        new ConsoleApp(printer::displayPromptFor).startConsoleApp();
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
