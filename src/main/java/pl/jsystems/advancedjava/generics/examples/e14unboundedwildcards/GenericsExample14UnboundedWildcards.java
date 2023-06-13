package pl.jsystems.advancedjava.generics.examples.e14unboundedwildcards;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

class GenericsExample14UnboundedWildcards
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericsExample14UnboundedWildcards.class);

    public static void main(String[] args)
    {
        GenericsExample14UnboundedWildcards experiments = new GenericsExample14UnboundedWildcards();
        experiments.run();
    }

    private void run()
    {
        LOGGER.info("How to handle '<?>'... ?");

        LOGGER.info("'<?>' is an unbounded wildcard. It is equivalent to <? extends Object>, " +
                "which means it's a behaves as upper bound.");

        List<String> listOfStrings = List.of("Just", "Strings", "here", "!");;
        List<?> listOfObjects = new ArrayList<>(listOfStrings);
        LOGGER.info("We can read from list of objects - for example first element of {} is {}",
                listOfObjects, listOfObjects.get(0));

        // The below does not compile - we cannot add a String there, we don't know what the list contains.
        // listOfObjects.add("a");

        LOGGER.info("So can we write to a List<?>? actually - we can.");
        LOGGER.info("Let's consider a function that duplicates its entries...");

        LOGGER.info("Duplicating 'List<?>' {}", listOfObjects);
        duplicateEntriesFor(listOfObjects);
        LOGGER.info("After writing duplicates we got {}", listOfObjects);
        LOGGER.info("That is only because we used type parameters, captured the wildcard using 'T'.");
        LOGGER.info("There is 'no other' way.");
    }

    private <T> void duplicateEntriesFor(List<T> listOfObjects)
    {
        int size = listOfObjects.size();
        for (int i = 0; i < size; i++) {
            T object = listOfObjects.get(i);
            listOfObjects.add(object);
        }

        // or we could do: listOfObjects.addAll(listOfObjects);
        // the above is for step-by-step explanation
    }
}
