package pl.jsystems.advancedjava.lambdas.solutions.s11functionalinterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

class LambdasExercise11FunctionalInterface
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LambdasExercise11FunctionalInterface.class);

    public static void main(String[] args)
    {
        TriPredicate<Integer> checkIfSortedAscending = (a, b, c) -> a <= b && b <= c;
        TriConsumer<Integer> processNumbers = (a, b, c) -> LOGGER.info("{}, {}, {}", a, b, c);
        TriPredicateBufferedFilter<Integer> sortedNumbersFilter = new TriPredicateBufferedFilter<>(checkIfSortedAscending, processNumbers);
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

        TriPredicate<Integer> areAllDivisibleBy3 = (a, b, c) -> a % 3 == 0 && b % 3 == 0 && c % 3 == 0;
        TriConsumer<Integer> by3Divider = (a, b, c) -> LOGGER.info("Results: {}, {}, {}", a / 3, b / 3, c / 3);
        TriPredicateBufferedFilter<Integer> by3DividerFilter = new TriPredicateBufferedFilter<>(areAllDivisibleBy3, by3Divider);
        by3DividerFilter.bufferIfMatches(3, 6, 9);
        by3DividerFilter.bufferIfMatches(422, 872, 192);
        by3DividerFilter.bufferIfMatches(171, 522, 558);
        by3DividerFilter.consumeAllMatched();
    }
}
