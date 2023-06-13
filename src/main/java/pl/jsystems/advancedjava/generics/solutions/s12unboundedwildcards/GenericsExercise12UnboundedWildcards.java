package pl.jsystems.advancedjava.generics.solutions.s12unboundedwildcards;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

class GenericsExercise12UnboundedWildcards
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericsExercise12UnboundedWildcards.class);

    public static void main(String[] args)
    {
        new GenericsExercise12UnboundedWildcards().run();
    }

    private void run()
    {
        LOGGER.info("'Reverse engineering' time...");

        List<?> example = new ArrayList<>(List.of("one", 2, 3d, 4L, new Object()));

        LOGGER.info("Reversing order of: {}", example);

        reverseOrder(example);
        LOGGER.info("Result: {}", example);
        otherReverseOrder(example);
        LOGGER.info("And again: {}", example);
    }

    <T> void reverseOrder(List<T> input)
    {
        List<T> tmp = new ArrayList<>(input);
        input.clear();
        for (int index = tmp.size() - 1; index >=0; index--) {
            input.add(tmp.get(index));
        }
    }

    <T> void otherReverseOrder(List<T> input)
    {
        int size = input.size() - 1;
        for (int index = 0; index < size / 2; index++) {
            T fromEnd = input.remove(size - index);
            T fromStart = input.remove(index);
            input.add(index, fromEnd);
            input.add(size - index, fromStart);
        }
    }
}
