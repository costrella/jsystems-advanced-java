package pl.jsystems.advancedjava.generics.examples.e2rawlistusage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

class GenericsExample2RawListUsage
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericsExample2RawListUsage.class);

    // !NOTE - this is how things worked before Java 5. There was no generics then.
    // Adding generics allowed for finding bugs earlier - at compilation time rather than runtime.
    // We now know the type of generic types and for example - cannot add unexpected types to list.
    // We should not be worried now about reading from the list (or other generic objects).

    // !NOTE - because Java is backward compatible (always have been), adding generics was not an easy task
    // and the approach taken can cause some serious confusion as to how things actually work under the hood.
    public static void main(String[] args)
    {
        GenericsExample2RawListUsage experiments = new GenericsExample2RawListUsage();
        experiments.createRawListOfStringsOneByOne();
        LOGGER.info("+++++++++");
        experiments.createRawListOfDifferentTypesOfObjectsOneByOne();
        LOGGER.info("+++++++++");
        experiments.createRawListOfDifferentTypesOfObjectsOneByOneForcingCastingException();
    }

    private void createRawListOfStringsOneByOne()
    {
        LOGGER.info("Creating raw list of Strings by adding elements one by one.");
        List listOfStrings = new ArrayList();
        listOfStrings.add("first");
        listOfStrings.add("second");
        listOfStrings.add("third");

        String firstElement = (String) listOfStrings.get(0);
        String secondElement = (String) listOfStrings.get(1);
        String thirdElement = (String) listOfStrings.get(2);

        LOGGER.info(firstElement);
        LOGGER.info(secondElement);
        LOGGER.info(thirdElement);
    }

    private void createRawListOfDifferentTypesOfObjectsOneByOne()
    {
        LOGGER.info("Creating raw list of random objects by adding elements one by one.");
        List listOfStrings = new ArrayList();
        listOfStrings.add("first");
        listOfStrings.add(new Object());
        listOfStrings.add(3L);

        String firstElement = (String) listOfStrings.get(0);
        Object secondElement = listOfStrings.get(1);
        Long thirdElement = (Long) listOfStrings.get(2);

        LOGGER.info(firstElement);
        LOGGER.info("{}", secondElement);
        LOGGER.info("{}", thirdElement);
    }

    private void createRawListOfDifferentTypesOfObjectsOneByOneForcingCastingException()
    {
        LOGGER.info("Creating raw list of random objects by adding elements one by one forcing casting exception.");
        List listOfStrings = new ArrayList();
        listOfStrings.add("first");
        listOfStrings.add(new Object());
        listOfStrings.add(3L);

        LOGGER.info("!The error below is expected!");
        String firstElement = (String) listOfStrings.get(0);
        Object secondElement = listOfStrings.get(1);
        Integer thirdElement = (Integer) listOfStrings.get(2);

        LOGGER.info(firstElement);
        LOGGER.info("{}", secondElement);
        LOGGER.info("{}", thirdElement);
    }
}
