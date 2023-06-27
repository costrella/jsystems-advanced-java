package pl.jsystems.advancedjava.lambdas.solutions.s12inlinelambda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

class LambdasExercise12InlineLambda
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LambdasExercise12InlineLambda.class);

    public static void main(String[] args)
    {
        TriPredicate<Number> checkIfSortedAscending = (a, b, c) -> a.doubleValue() <= b.doubleValue() && b.doubleValue() <= c.doubleValue();
        TriConsumer<Number> processNumbers = (a, b, c) -> LOGGER.info("{}, {}, {}", a, b, c);
        TriPredicateBufferedFilter<Number> sortedNumbersFilter = new TriPredicateBufferedFilter<>(checkIfSortedAscending, processNumbers);
        sortedNumbersFilter.bufferIfMatches(2, 2, 3);
        sortedNumbersFilter.bufferIfMatches(3, 3, 1);
        sortedNumbersFilter.bufferIfMatches(8, 4, 11);
        sortedNumbersFilter.bufferIfMatches(42, 42, 42);
        sortedNumbersFilter.consumeAllMatched();

        TriPredicate<String> allNotBlank = (a, b, c) -> !a.isBlank() && !b.isBlank() && !c.isBlank();
        TriConsumer<String> notBlankPrinter = (a, b, c) -> LOGGER.info("{}", String.join("-", a, b, c));
        TriPredicateBufferedFilter<String> notBlankPrinterFilter = new TriPredicateBufferedFilter<>(allNotBlank, notBlankPrinter);
        notBlankPrinterFilter.bufferIfMatches("    ", " ", "a");
        notBlankPrinterFilter.bufferIfMatches("1", "_", "b");
        notBlankPrinterFilter.bufferIfMatches("4", "2", "!");
        notBlankPrinterFilter.consumeAllMatched();

        TriPredicate<Object> areAllEqual = (a, b, c) -> a.equals(b) && b.equals(c);
        TriConsumer<Object> simpleHashCodePrinter = (a, b, c) -> LOGGER.info("HashCodes: {}, {} {} {}", a, a.hashCode(), b.hashCode(), c.hashCode());
        TriPredicateBufferedFilter<Object> simpleEqualityCheckFilter = new TriPredicateBufferedFilter<>(areAllEqual, simpleHashCodePrinter);
        simpleEqualityCheckFilter.bufferIfMatches(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        simpleEqualityCheckFilter.bufferIfMatches(new Object(), new Object(), new Object());
        simpleEqualityCheckFilter.bufferIfMatches("a", "a", "a");
        simpleEqualityCheckFilter.consumeAllMatched();

        TriPredicateBufferedFilter<Integer> by3DividerFilter = new TriPredicateBufferedFilter<>(
                (a, b, c) -> a % 3 == 0 && b % 3 == 0 && c % 3 == 0,
                (a, b, c) -> LOGGER.info("Results: {}, {}, {}", a / 3, b / 3, c / 3));
        by3DividerFilter.bufferIfMatches(3, 6, 9);
        by3DividerFilter.bufferIfMatches(422, 872, 192);
        by3DividerFilter.bufferIfMatches(171, 522, 558);
        by3DividerFilter.consumeAllMatched();

        TriPredicateBufferedFilter<String> printJoinedIfEqualFilter = new TriPredicateBufferedFilter<>(
                areAllEqual,
                notBlankPrinter
        );
        printJoinedIfEqualFilter.bufferIfMatches("a", "a", "a");
        printJoinedIfEqualFilter.bufferIfMatches("1", "2", "3");
        printJoinedIfEqualFilter.bufferIfMatches("42", "42", "42");

        TriPredicateBufferedFilter<Integer> printHashcodeIfSorted = new TriPredicateBufferedFilter<>(
                checkIfSortedAscending,
                simpleHashCodePrinter
        );

        printHashcodeIfSorted.bufferIfMatches(1, 2, 3);
        printHashcodeIfSorted.bufferIfMatches(5, 4, 5);
        printHashcodeIfSorted.bufferIfMatches(1, 1, 1);
    }
}
