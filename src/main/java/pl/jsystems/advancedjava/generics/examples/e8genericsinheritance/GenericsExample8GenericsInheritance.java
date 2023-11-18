package pl.jsystems.advancedjava.generics.examples.e8genericsinheritance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

class GenericsExample8GenericsInheritance
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericsExample8GenericsInheritance.class);

    public static void main(String[] args)
    {
        GenericsExample8GenericsInheritance experiments = new GenericsExample8GenericsInheritance();
        experiments.run();
    }

    private void run()
    {
        LOGGER.info("Let's see if List<Integer> does extend List<Number>.");

        LOGGER.info("Let's consider typical inheritance - child has all of the parents functionality + possibly something more.");
        LOGGER.info("Now parent is doing stuff. What can parent do?");
        Parent parent = new Parent();
        parent.add(1);
        parent.add(1d);
        parent.add(1L);
        parent.add(BigDecimal.ONE);

        LOGGER.info("Now child1 is doing stuff. What can child1 do?");
        Child1 child1 = new Child1();
        child1.add(1);
        child1.add(1d);
        child1.add(1L);
        child1.add(BigDecimal.ONE);

        LOGGER.info("Now child2 is doing stuff. What can child2 do?");
        Child2 child2 = new Child2();
        child2.add(1);
        child2.add(1d);
        child2.add(1L);
        child2.add(BigDecimal.ONE);

        LOGGER.info("========");
        LOGGER.info("Now, let's consider List<Integer> vs List<Number>. What can those do?");
        LOGGER.info("First - list of Numbers");
        List<Number> listOfNumbers = new ArrayList<>();
        listOfNumbers.add(1);
        listOfNumbers.add(1d);
        listOfNumbers.add(1L);
        listOfNumbers.add(BigDecimal.ONE);
        LOGGER.info("List of numbers accepted different sub-types of Number: {}.", listOfNumbers);

        List<Integer> listOfIntegers = new ArrayList<>();
        listOfIntegers.add(1);
        // The below does not work!
        // listOfIntegers.add(1d);
        // listOfIntegers.add(1L);
        // listOfIntegers.add(BigDecimal.ONE);
        LOGGER.info("List of numbers accepted only Integer: {}.", listOfIntegers);
        LOGGER.info("List<Integer> can read integers - so it extends List<Number> in that sense.");
        LOGGER.info("List<Integer> does not accept other types of Numbers, only Integers.");
        LOGGER.info("List<Integer> narrows functionality of List<Number> in that sense.");
        LOGGER.info("If List<Number> wasn't able to add other numeric types, then List<Integer> could extend it.");
        LOGGER.info("List<Integer> 'is a' list of ONLY Integers. List<Integer> 'is not a' List of any number, List<Number>.");
        LOGGER.info("There is no 'is a' relationship, so it's not an inheritance.");
    }

    void populateWithTemperatureReadingsFromStation(List<? super Double> resultList, Long stationId) {
        List<Double> temperatureReadings = new ArrayList<>();
        resultList.add(temperatureReadings.get(0));
        resultList.add(temperatureReadings.get(1));
        resultList.add(temperatureReadings.get(2));
    }

    void someMethod()
    {
        Long someStationId = 1L;
        List<Number> temperatureReadingsHolder = new ArrayList<>();
        populateWithTemperatureReadingsFromStation(temperatureReadingsHolder, someStationId);
    }

    void someOtherMethod()
    {
        Long someStationId = 2L;
        List<Double> temperatureReadingsHolder = new ArrayList<>();
        populateWithTemperatureReadingsFromStation(temperatureReadingsHolder, someStationId);
    }

    private static class Parent
    {
        void add(Number value)
        {
            LOGGER.info("Adding number value in parent: " + value);
        }
    }

    private static class Child1 extends Parent
    {
        void add(Double value)
        {
            LOGGER.info("Adding double value in child1: " + value);
        }
    }

    private static class Child2 extends Parent
    {
        void add(Long value)
        {
            LOGGER.info("Adding long number value in child2: " + value);
        }
    }
}