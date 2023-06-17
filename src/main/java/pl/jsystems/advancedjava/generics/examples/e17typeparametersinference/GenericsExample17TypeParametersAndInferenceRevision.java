package pl.jsystems.advancedjava.generics.examples.e17typeparametersinference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class GenericsExample17TypeParametersAndInferenceRevision
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericsExample17TypeParametersAndInferenceRevision.class);

    public static void main(String[] args)
    {
        GenericsExample17TypeParametersAndInferenceRevision experiments = new GenericsExample17TypeParametersAndInferenceRevision();
        experiments.run();
    }

    private void run()
    {
        LOGGER.info("Pair with restricted type parameters.");
        // the below does not compile, since second parameter should be subtype of the first one.
        //var pair1 = new Pair<>(3d, "3");

        // because we use 'var', we don't specify which type we 'want' - compiler decides that
        // it can upcast double to Number to satisfy Pair class requirements.
        var pair1 = new Pair<>(3d, 3);
        LOGGER.info("double-int pair can be created: {}", pair1);

        // the below does not compile, since second parameter should be subtype of the first one.
        // type inference makes VALUE1 be Double - cannot be upcasted to Number.
        // Pair<Double, Integer> pair2 = new Pair<>(3d, 3);

        Pair<Number, Number> pair2 = new Pair<>(3d, 3);
        LOGGER.info("Number-number pair can be created: {}", pair2);
    }
}
