package pl.jsystems.advancedjava.generics.examples.e5genericmethods;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class GenericsExample5GenericMethods
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericsExample5GenericMethods.class);

    public static void main(String[] args)
    {
        GenericsExample5GenericMethods experiments = new GenericsExample5GenericMethods();
        experiments.run();
    }

    private void run()
    {
        LOGGER.info("Let's use some generic methods!");
        Pair<String, Integer> somePair = createPair("Some value", 42);
        LOGGER.info("Some pair created using generic method: {}", somePair);
        Pair<String, String> someOtherPair = createPair("Some value", "42");
        LOGGER.info("Other pair created using generic method: {}", someOtherPair);
        Pair<Integer, String> anotherPair = createPair(42, "42");
        LOGGER.info("Yet another pair created using generic method: {}", anotherPair);
        
        LOGGER.info("We're expecting exception here!");
        createPair(null, "42");
    }

    private <T, R> Pair<T, R> createPair(T value1, R value2)
    {
        LOGGER.info("Creating pair with values: {}, {}", value1, value2);
        return Pair.createPair(value1, value2);
    }
}
