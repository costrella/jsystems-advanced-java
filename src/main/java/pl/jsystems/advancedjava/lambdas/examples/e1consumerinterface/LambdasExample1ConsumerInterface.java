package pl.jsystems.advancedjava.lambdas.examples.e1consumerinterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

class LambdasExample1ConsumerInterface
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LambdasExample1ConsumerInterface.class);

    public static void main(String[] args)
    {
        PromptPrinter promptPrinter = new PromptPrinter();
        AuditLogger auditLogger = new AuditLogger();

        Consumer<String> inputConsumer = new AfterConsoleInputRunner(promptPrinter, auditLogger);
        ConsoleApp app = new ConsoleApp(inputConsumer);
        app.startConsoleApp();

        Consumer<String> otherInputConsumer = new OtherConsumer(promptPrinter);
        ConsoleApp otherApp = new ConsoleApp(otherInputConsumer);
        otherApp.startConsoleApp();
    }

    static class AfterConsoleInputRunner implements Consumer<String>
    {
        private final PromptPrinter printer;
        private final AuditLogger auditLogger;

        AfterConsoleInputRunner(PromptPrinter displayer, AuditLogger auditLogger)
        {
            this.printer = displayer;
            this.auditLogger = auditLogger;
        }

        @Override
        public void accept(String input)
        {
            // we can do whatever we want here.
            printer.displayPromptFor(input);
            auditLogger.log(input);
        }
    }

    static class OtherConsumer implements Consumer<String>
    {
        private final PromptPrinter displayer;

        OtherConsumer(PromptPrinter displayer)
        {
            this.displayer = displayer;
        }

        @Override
        public void accept(String input)
        {
            // we can do whatever we want here.
            displayer.displayPromptFor(input);
        }
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
