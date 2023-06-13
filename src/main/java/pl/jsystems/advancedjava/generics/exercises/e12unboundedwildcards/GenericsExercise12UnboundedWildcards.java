package pl.jsystems.advancedjava.generics.exercises.e12unboundedwildcards;

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

        // reverseOrder(example);
        // LOGGER.info("Result: {}", example);
    }
}
