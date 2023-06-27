package pl.jsystems.advancedjava.lambdas.exercises.e11functionalinterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class LambdasExercise11FunctionalInterface
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LambdasExercise11FunctionalInterface.class);

    public static void main(String[] args)
    {
        // todo - odkomentuj i dokoncz przykład

        /*
        TriPredicate<Integer> checkIfSortedAscending = null;// todo - sprawdz czy liczby są posortowane rosnąco
        TriConsumer<Integer> processNumbers = null; // todo - wyświetl liczby
        TriPredicateBufferedFilter<Integer> sortedNumbersFilter = new TriPredicateBufferedFilter<>(checkIfSortedAscending, processNumbers);

        sortedNumbersFilter.bufferIfMatches(2, 2, 3);
        sortedNumbersFilter.bufferIfMatches(3, 3, 1);
        sortedNumbersFilter.bufferIfMatches(8, 4, 11);
        sortedNumbersFilter.bufferIfMatches(42, 42, 42);
        sortedNumbersFilter.consumeAllMatched();
        */


        // todo  - odkomentuj i dokoncz, więcej przykładów

        /*
        TriPredicate<String> allNotBlank = null; // todo - sprawdz czy wszystkie Stringi zawierają jakiś znak poza pustym (blank).
        TriConsumer<String> notBlankPrinter = null; // todo - wyświetl wszystkie zlączone znakiem '-'
        TriPredicateBufferedFilter<String> notBlankPrinterFilter = new TriPredicateBufferedFilter<>(allNotBlank, notBlankPrinter);
        notBlankPrinterFilter.bufferIfMatches("    ", " ", "a");
        notBlankPrinterFilter.bufferIfMatches("1", "_", "b");
        notBlankPrinterFilter.bufferIfMatches("4", "2", "!");
        notBlankPrinterFilter.consumeAllMatched();

        TriPredicate<Object> areAllEqual = null; // todo - sprawdz czy wszystkie obiekty są równe (equals)
        TriConsumer<Object> simpleHashCodePrinter = null; // todo - wyswietl hashCode() każdego
        TriPredicateBufferedFilter<Object> simpleEqualityCheckFilter = new TriPredicateBufferedFilter<>(areAllEqual, simpleHashCodePrinter);
        simpleEqualityCheckFilter.bufferIfMatches(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        simpleEqualityCheckFilter.bufferIfMatches(new Object(), new Object(), new Object());
        simpleEqualityCheckFilter.bufferIfMatches("a", "a", "a");
        simpleEqualityCheckFilter.consumeAllMatched();

        TriPredicate<Integer> areAllDivisibleBy3 = null; // todo - sprawdz czy liczby są podzielne przez 3
        TriConsumer<Integer> by3Divider = null; // todo - wyswietl wyniki dzielenia uzyciem toString()
        TriPredicateBufferedFilter<Integer> by3DividerFilter = new TriPredicateBufferedFilter<>(areAllDivisibleBy3, by3Divider);
        by3DividerFilter.bufferIfMatches(3, 6, 9);
        by3DividerFilter.bufferIfMatches(422, 872, 192);
        by3DividerFilter.bufferIfMatches(171, 522, 558);
        by3DividerFilter.consumeAllMatched();

        */
    }
}
