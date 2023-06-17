package pl.jsystems.advancedjava.lambdas.examples.e6predicateinterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

class LambdasExample6PredicateInterface
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LambdasExample6PredicateInterface.class);

    public static void main(String[] args)
    {
        PromptPrinter printer = new PromptPrinter();
        AuditLogger auditLogger = new AuditLogger();

        Consumer<String> firstConsumer = input ->
        {
            printer.displayPromptFor(input);
            auditLogger.log(input);
        };
        new ConsoleApp(firstConsumer, "q"::equals).startConsoleApp();
        new ConsoleApp(printer::displayPromptFor, "quit"::equals).startConsoleApp();
        new ConsoleApp(printer::displayPromptFor, String::isBlank).startConsoleApp();
        new ConsoleApp(printer::displayPromptFor, input -> input.length() == 5).startConsoleApp();
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
