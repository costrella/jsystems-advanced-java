package pl.jsystems.advancedjava.generics.solutions.s14multipletypeparameters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

class GenericsExercise14MultipleTypeParameters
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericsExercise14MultipleTypeParameters.class);

    public static void main(String[] args)
    {
        new GenericsExercise14MultipleTypeParameters().run();
    }

    private void run()
    {
        LOGGER.info("Merge...");

        List<Number> listWithNumbers = List.of(1, 2d, 3L, 4f);
        LOGGER.info("Created base list: {}", listWithNumbers);

        List<Number> otherListOfNumbers = List.of(1, 2, 3, 4);
        List<Number> mergedList1 = mergeUnique(listWithNumbers, otherListOfNumbers);
        LOGGER.info("Merged base list: {}, with {}, result:{}", listWithNumbers, otherListOfNumbers, mergedList1);

        List<Integer> listOfIntegers = List.of(1, 2, 3, 4);
        List<Number> mergedList2 = mergeUnique(listWithNumbers, listOfIntegers);
        LOGGER.info("Merged base list: {}, with {}, result:{}", listWithNumbers, listOfIntegers, mergedList2);

        List<Long> listOfLongs = List.of(1L, 2L, 3L, 4L);
        List<Number> mergedList3 = mergeUnique(listWithNumbers, listOfLongs);
        LOGGER.info("Merged base list: {}, with {}, result:{}", listWithNumbers, listOfLongs, mergedList3);

        // the below should not work!!
        // var result = mergeUnique(listWithNumbers, List.of("THIS SHOULD NOT WORK"));

        // this works for case where T does not extend Number (part 1 of the exercise)
        // List<Object> listWithObjects = List.of(1, 2d, "3", 4L);
        // LOGGER.info("Created new base list of objects: {}", listWithObjects);
        // List<Object> mergedList4 = mergeUnique(listWithObjects, listOfLongs);
        // LOGGER.info("Merged base list: {}, with {}, result:{}", listWithNumbers, listOfLongs, mergedList4);

        List<Integer> sameListsMerged = mergeUnique(listOfIntegers, listOfIntegers);
        LOGGER.info("Merged list with itself {} - result: {}", listOfIntegers, sameListsMerged);
    }

    <T extends Number, R extends T> List<T> mergeUnique(List<T> values, List<R> newValues)
    {
        List<T> result = new ArrayList<>(values);
        for (T newValue : newValues)
        {
            if (!result.contains(newValue))
            {
                result.add(newValue);
            }
        }

        return result;
    }
}
