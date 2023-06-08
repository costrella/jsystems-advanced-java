package pl.jsystems.advancedjava.generics.examples.e1simplelistusage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

class GenericsExample1SimpleListUsage
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericsExample1SimpleListUsage.class);

    public static void main(String[] args)
    {
        GenericsExample1SimpleListUsage experiments = new GenericsExample1SimpleListUsage();
        experiments.createListOneByOne();
        LOGGER.info("+++++++++");
        experiments.createListUsingListOf();
        LOGGER.info("+++++++++");
        experiments.createListOfObjects();
        LOGGER.info("+++++++++");
        experiments.createListOfObjectsUsingCastingWhileReading();
        LOGGER.info("+++++++++");
        experiments.createListOfObjectsUsingCastingWhileReadingForcingException();
    }

    private void createListOneByOne()
    {
        LOGGER.info("Creating list of Strings by adding elements one by one.");
        List<String> listOfStrings = new ArrayList<>();
        listOfStrings.add("first");
        listOfStrings.add("second");
        listOfStrings.add("third");

        String firstElement = listOfStrings.get(0);
        String secondElement = listOfStrings.get(1);
        String thirdElement = listOfStrings.get(2);

        LOGGER.info(firstElement);
        LOGGER.info(secondElement);
        LOGGER.info(thirdElement);
    }

    private void createListUsingListOf()
    {
        LOGGER.info("Creating list of Strings using List.of().");
        List<String> listOfStrings = List.of("first", "second", "third");

        String firstElement = listOfStrings.get(0);
        String secondElement = listOfStrings.get(1);
        String thirdElement = listOfStrings.get(2);

        LOGGER.info(firstElement);
        LOGGER.info(secondElement);
        LOGGER.info(thirdElement);
    }

    private void createListOfObjects()
    {
        LOGGER.info("Creating list of Objects using List.of().");

        List<Object> listOfObjects = List.of("first", "second", 42);

        String firstElement = listOfObjects.get(0).toString();
        String secondElement = listOfObjects.get(1).toString();
        String thirdElement = listOfObjects.get(2).toString();

        LOGGER.info(firstElement);
        LOGGER.info(secondElement);
        LOGGER.info(thirdElement);
    }

    private void createListOfObjectsUsingCastingWhileReading()
    {
        LOGGER.info("Creating list of Objects - reading with casting.");

        List<Object> listOfObjects = List.of("first", "second", 42);

        String firstElement = (String) listOfObjects.get(0);
        String secondElement = (String) listOfObjects.get(1);
        Integer thirdElement = (Integer) listOfObjects.get(2);

        LOGGER.info(firstElement);
        LOGGER.info(secondElement);
        LOGGER.info("{}", thirdElement);
    }

    private void createListOfObjectsUsingCastingWhileReadingForcingException()
    {
        LOGGER.info("Creating list of Objects - reading with invalid casting.");

        List<Object> listOfObjects = List.of("first", "second", 42);

        LOGGER.info("!The error below is expected!");
        String firstElement = (String) listOfObjects.get(0);
        String secondElement = (String) listOfObjects.get(1);
        String thirdElement = (String) listOfObjects.get(2);

        LOGGER.info(firstElement);
        LOGGER.info(secondElement);
        LOGGER.info(thirdElement);
    }
}
