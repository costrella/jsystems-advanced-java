package pl.jsystems.advancedjava.lambdas.examples.e2anonymousclasses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

class LambdasExample2AnonymousClasses
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LambdasExample2AnonymousClasses.class);

    public static void main(String[] args)
    {
        PromptPrinter printer = new PromptPrinter();
        AuditLogger auditLogger = new AuditLogger();

        new ConsoleApp(new Consumer<String>()
        {
            @Override
            public void accept(String input)
            {
                printer.displayPromptFor(input);
                auditLogger.log(input);
            }
        }).startConsoleApp();

        new ConsoleApp(new Consumer<String>()
        {
            @Override
            public void accept(String input)
            {
                printer.displayPromptFor(input);
            }
        }).startConsoleApp();
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
