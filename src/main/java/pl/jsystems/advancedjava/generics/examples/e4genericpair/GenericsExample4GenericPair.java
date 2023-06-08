package pl.jsystems.advancedjava.generics.examples.e4genericpair;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class GenericsExample4GenericPair
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericsExample4GenericPair.class);

    public static void main(String[] args)
    {
        GenericsExample4GenericPair experiments = new GenericsExample4GenericPair();
        experiments.run();
    }

    private void run()
    {
        LOGGER.info("Creating the pair object.");
        Pair<String, Integer> somePair = new Pair<>("Mount Everest height", 8849);
        LOGGER.info("Created pair: {}", somePair);
        String firstValue = somePair.getFirstValue();
        Integer secondValue = somePair.getSecondValue();
        LOGGER.info("Pair values: {} - {}", firstValue, secondValue);

        Pair<Integer, String> otherPair = new Pair<>(2023, "Current year");
        LOGGER.info("Created pair: {}", otherPair);
        Integer firstValueFromOtherPair = otherPair.getFirstValue();
        String secondValueFromOtherPair = otherPair.getSecondValue();
        LOGGER.info("Pair values: {} - {}", firstValueFromOtherPair, secondValueFromOtherPair);
    }
}
