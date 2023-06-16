package pl.jsystems.advancedjava.generics.solutions.s11lowerboundwildcard;

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
        addIfDoesntExist(42, listOfIntegers);
        addIfDoesntExist(42, listOfNumbers);
        addIfDoesntExist(42, listOfObjects);

        addIfDoesntExistWithTypeParameter(42, listOfIntegers);
        addIfDoesntExistWithTypeParameter(42, listOfNumbers);
        addIfDoesntExistWithTypeParameter(42, listOfObjects);

        addIfDoesntExistWithBoundedTypeParameter(42, listOfIntegers);
        addIfDoesntExistWithBoundedTypeParameter(42, listOfNumbers);
        addIfDoesntExistWithBoundedTypeParameter(42, listOfObjects);
    }

    void addIfDoesntExist(Integer newValue, List<? super Integer> values)
    {
        if (!values.contains(newValue))
        {
            values.add(newValue);
        }
    }

    // List<? super T> is redundant - T can always be upcasted to whatever type List<T> is
    <T> void addIfDoesntExistWithTypeParameter(T newValue, List<T> values)
    {
        if (!values.contains(newValue))
        {
            values.add(newValue);
        }
    }

    <T extends Number> void addIfDoesntExistWithBoundedTypeParameter(T newValue, List<? super T> values)
    {
        if (!values.contains(newValue))
        {
            values.add(newValue);
        }
    }
}
