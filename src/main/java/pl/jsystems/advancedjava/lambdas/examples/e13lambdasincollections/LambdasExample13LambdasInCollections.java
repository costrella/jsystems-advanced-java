package pl.jsystems.advancedjava.lambdas.examples.e13lambdasincollections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

class LambdasExample13LambdasInCollections
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LambdasExample13LambdasInCollections.class);

    public static void main(String[] args)
    {
        PromptPrinter printer = new PromptPrinter();
        AuditLogger auditLogger = new AuditLogger();

        new ConsoleApp(List.of(printer::displayPromptFor, auditLogger::log)).startConsoleApp();
        new ConsoleApp(List.of(printer::displayPromptFor)).startConsoleApp();
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
