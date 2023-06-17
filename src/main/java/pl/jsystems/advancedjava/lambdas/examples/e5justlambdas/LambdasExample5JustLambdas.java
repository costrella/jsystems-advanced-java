package pl.jsystems.advancedjava.lambdas.examples.e5justlambdas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Consumer;

class LambdasExample5JustLambdas
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LambdasExample5JustLambdas.class);

    public static void main(String[] args)
    {
        PromptPrinter printer = new PromptPrinter();
        AuditLogger auditLogger = new AuditLogger();

        Consumer<String> firstConsumer = input ->
        {
            printer.displayPromptFor(input);
            auditLogger.log(input);
        };
        new ConsoleApp(firstConsumer).startConsoleApp();

        Consumer<String> secondConsumer = printer::displayPromptFor;
        new ConsoleApp(secondConsumer).startConsoleApp();

        // we can use () around the argument - () are required for lambdas with 0 or more than 2 arguments
        Consumer<String> thirdConsumer = (input) -> LOGGER.info("NEW INPUT {}!", input);
        new ConsoleApp(thirdConsumer).startConsoleApp();

        Consumer<String> fourthConsumer = (input) -> {};
        new ConsoleApp(fourthConsumer).startConsoleApp();

        Runnable mostBasicLambda = () -> {};
        LOGGER.info("Running most basic (noop) lambda.");
        mostBasicLambda.run();
        LOGGER.info("As if nothing happened...");

        // {} are not required and not advised for 1 line lambda expressions, unless it's noop lambda
        LOGGER.info("Running other runnable.");
        Runnable otherRunnable = () -> {LOGGER.info("Someone called me!");};
        otherRunnable.run();

        LOGGER.info("Running most basic (noop) lambda for as MyRunnable.");
        MyRunnable mostBasicLambdaAgain = () -> {};
        mostBasicLambdaAgain.run();
        LOGGER.info("And also - nothing happened.");

        MyConsumer<List<String>> myConsumer = input -> LOGGER.info("Got something: {}", input);
        LOGGER.info("My consumer for input: {}", List.of("1", "2", "3"));
        myConsumer.take(List.of("1", "2", "3"));

        MyNumberConsumer<Integer> myNumberConsumer = input -> LOGGER.info("Got a number: {}", input);
        LOGGER.info("My number consumer for input: {}", 42);
        myNumberConsumer.use(42);

        NumericOperation<Integer> addition = (n1, n2) -> n1 + n2;
        LOGGER.info("Let's add two numbers: {}, {} - result: {}", 40, 2, addition.calculate(40, 2));
        NumericOperation<Integer> sameAddition = Integer::sum;
        LOGGER.info("Let's add two numbers(using method reference): {}, {} - result: {}",
                40, 2, sameAddition.calculate(40, 2));

        NumericOperation<Integer> multiplication = (n1, n2) -> n1 * n2;
        LOGGER.info("Let's multiply two numbers: {}, {} - result: {}",
                21, 2, multiplication.calculate(21, 2));

        NumericOperation<Integer> multiplicationByAdding = (n1, n2) ->
        {
            int sum = 0;
            for (int i = 0; i < Math.abs(n2); i++)
            {
                sum += n1;
            }
            if (n2 < 0)
            {
                sum *= -1;
            }
            return sum;
        };
        LOGGER.info("Let's multiply two numbers (by addition): {}, {} - result: {}",
                100, 42, multiplicationByAdding.calculate(100, 42));
        LOGGER.info("Let's multiply two numbers (by addition): {}, {} - result: {}",
                100, -42, multiplicationByAdding.calculate(100, -42));
        LOGGER.info("Let's multiply two numbers (by addition): {}, {} - result: {}",
                -100, 42, multiplicationByAdding.calculate(-100, 42));
        LOGGER.info("Let's multiply two numbers (by addition): {}, {} - result: {}",
                -100, -42, multiplicationByAdding.calculate(-100, -42));
    }

    interface MyRunnable
    {
        void run();
    }

    interface MyConsumer<T>
    {
        void take(T argument);
    }

    interface MyNumberConsumer<T extends Number>
    {
        void use(T argument);
    }

    interface NumericOperation<T extends Number>
    {
        T calculate(T number1, T number2);
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
