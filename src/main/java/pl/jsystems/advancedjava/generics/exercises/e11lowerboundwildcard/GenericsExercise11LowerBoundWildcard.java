package pl.jsystems.advancedjava.generics.exercises.e11lowerboundwildcard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

class GenericsExercise11LowerBoundWildcard
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericsExercise11LowerBoundWildcard.class);

    public static void main(String[] args)
    {
        new GenericsExercise11LowerBoundWildcard().run();
    }

    private void run()
    {
        LOGGER.info("It's 'super' time!");
        List<Integer> listOfIntegers = List.of(4, 2, 44, 22);
        List<Number> listOfNumbers = List.of(42d, 42L, 42f);
        List<Object> listOfObjects = List.of("42", 42d);

        // TODO - make the below work
        // addIfDoesntExist(42, listOfIntegers);
        // addIfDoesntExist(42, listOfNumbers);
        // addIfDoesntExist(42, listOfObjects);
    }
}
