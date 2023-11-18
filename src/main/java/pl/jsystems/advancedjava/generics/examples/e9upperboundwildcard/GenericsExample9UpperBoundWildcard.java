package pl.jsystems.advancedjava.generics.examples.e9upperboundwildcard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

class GenericsExample9UpperBoundWildcard
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericsExample9UpperBoundWildcard.class);

    public static void main(String[] args)
    {
        GenericsExample9UpperBoundWildcard experiments = new GenericsExample9UpperBoundWildcard();
        experiments.run();
    }

    private void run()
    {
        LOGGER.info("Ok, let's focus now... This is important, if not crucial!");
        List<Number> listOfNumbers = new ArrayList<>();
        listOfNumbers.add(1);
        listOfNumbers.add(2d);
        listOfNumbers.add(3L);
        listOfNumbers.add(BigDecimal.ZERO);

        double sumOfNumbers = findMax(listOfNumbers);
        LOGGER.info("The result of the max operation of Numbers {} is: {}", listOfNumbers, sumOfNumbers);

        List<Integer> listOfIntegers = new ArrayList<>();
        listOfIntegers.add(1);
        listOfIntegers.add(2);
        listOfIntegers.add(3);
        listOfIntegers.add(36);

        // NOTE!!!
        // the below does not work. As discussed in previous example -
        // even though Integer extends Number List<Integer> (!)DOES NOT(!) extend List<Number>
        //double sumOfIntegers = sumNumbers(listOfIntegers);

        // NOTE!!!
        // remember from the last example:
        // List<Integer> narrows functionality of List<Number> in the sense it cannot add other numeric types.
        // If List<Number> weren't able to add other numeric types, then List<Integer> could extend it.");
        // What if such type existed - readonly list of Numbers.
        // For example: List<? extends Number> - list of things (exact type unknown) that extend from Number.
        // This way we can read the number, but we cannot add one (we don't know which type to add).
        // This way List<Integer> extends List<? extends Number>!
        double sumOfIntegers = findMaxUsingMethodWithWildcardArgument(listOfIntegers);
        LOGGER.info("The result of the max operation of Integers {} is: {}", listOfIntegers, sumOfIntegers);

        List<Long> listOfLongs = List.of(1L, 2L, 3L, 4L);
        double sumOfLongs = findMaxUsingMethodWithWildcardArgument(listOfLongs);
        LOGGER.info("The result of the max operation of Longs {} is: {}", listOfLongs, sumOfLongs);

        double otherSumOfNumbers = findMaxUsingMethodWithWildcardArgument(listOfNumbers);
        LOGGER.info("The result of the max operation of Numbers {} is: {}", listOfNumbers, otherSumOfNumbers);
    }

    private double findMax(List<Number> listOfNumbers)
    {
        // NOTE!
        // We can do whatever we want with the list here
        listOfNumbers.add(Double.MIN_VALUE); // just to show that we can add to the list
        listOfNumbers.remove(Double.MIN_VALUE);

        double max = Double.MIN_VALUE;
        for (Number number : listOfNumbers)
        {
            if (number.doubleValue() > max)
            {
                max = number.doubleValue();
            }
        }
        return max;
    }

    private double findMaxUsingMethodWithWildcardArgument(List<? extends Number> listOfNumbers)
    {
        // NOTE!
        // We cannot do much with the list now - we can query it, but cannot add* any new numbers to it.
        // We don't know if we have list of Numbers, Doubles, Integers etc!
        // * we can add null
        // listOfNumbers.add(0d); <- doesn't work
        double max = Double.MIN_VALUE;
        for (Number number : listOfNumbers)
        {
            if (number.doubleValue() > max)
            {
                max = number.doubleValue();
            }
        }
        return max;
    }
}