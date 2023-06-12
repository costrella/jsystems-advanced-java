package pl.jsystems.advancedjava.generics.examples.e13lowerboundwildcard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

class GenericsExample13LowerBoundWildcard
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericsExample13LowerBoundWildcard.class);

    public static void main(String[] args)
    {
        GenericsExample13LowerBoundWildcard experiments = new GenericsExample13LowerBoundWildcard();
        experiments.run();
    }

    private void run()
    {
        LOGGER.info("Ok, let's focus again... This is important, if not crucial!");

        List<Number> listOfNumbers = new ArrayList<>(List.of(1L, 2d, 3f, 4, BigDecimal.TEN));
        LOGGER.info("We have a list of numbers values (List<Number>): {}", listOfNumbers);
        LOGGER.info("It is a list that Long value can be part of, nothing prevents it");
        List<? super Long> listWhereLongIsAcceptable = listOfNumbers;
        LOGGER.info("It is also a list that Integer value can be part of (List<? super Long>), nothing prevents it.");
        List<? super Integer> listWhereIntegerIsAcceptable = listOfNumbers;
        LOGGER.info("If we know that it can store Integers, we might add one!");
        listWhereIntegerIsAcceptable.add(42);
        LOGGER.info("Updated list using 'List<? super Integer>' - {}", listOfNumbers);
        listWhereLongIsAcceptable.add(142L);
        LOGGER.info("Updated list using 'List<? super Long>' - {}", listOfNumbers);
        LOGGER.info("We, however, cannot read from that list (unless we cast to Object).");
        LOGGER.info("Why? Because we don't know if there is a Long, or maybe other Number or an Object there.");
        List<Object> listOfObjects = new ArrayList<>(List.of(new Object(), 1d, "abcd"));
        LOGGER.info("We can also have a list of Objects - {}", listOfObjects);
        LOGGER.info("Number values are Objects - so we can put them in the list (using List<? super Double>)!");
        List<? super Number> listWhereNumberIsAcceptable = listOfObjects;
        listWhereNumberIsAcceptable.add(1142d);
        listWhereNumberIsAcceptable.add(1);
        listWhereNumberIsAcceptable.add(2f);
        LOGGER.info("Updated list of objects - {}", listOfObjects);
        LOGGER.info("We can read from the list, but only assuming that the result is of type Object.");
        Object first = listOfObjects.get(0);
        Object second = listOfObjects.get(0);
        Object third = listOfObjects.get(0);
        // we cannot read as anything else, the below does not work. What would we expect. It can be an object of type Object.
        // Number firstNumber = listWhereNumberIsAcceptable.get(0);
        LOGGER.info("First three elements of the list of objects are - {}, {}, {}", first, second, third);

        LOGGER.info("========REMEMBER THIS=========");
        LOGGER.info("<? super TYPE> - means it's safe to write / add / provide objects of type TYPE to it, as well as subtypes of TYPE.");
        LOGGER.info("<? super TYPE> - means we don't know exactly what is there, so we ony read / get / receive from it expecting values of type Object.");
        LOGGER.info("==============================");

        List<Double> doubles = new ArrayList<>();
        addRandomNumbersAtTheEndOf(doubles, 10);
        LOGGER.info("Generated random numbers {}", doubles);

        List<Number> numbers = new ArrayList<>();
        addRandomNumbersAtTheEndOf(numbers, 5);
        LOGGER.info("Generated random numbers {}", numbers);

    }

    private void addRandomNumbersAtTheEndOf(List<? super Double> numbers, int amount)
    {
        for (int i = 0; i < amount; i++) {
            numbers.add(RandomGenerator.getDefault().nextDouble());
        }
    }
}
