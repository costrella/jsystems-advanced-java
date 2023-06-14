package pl.jsystems.advancedjava.generics.examples.e16nonreifiabletypes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

class GenericsExample16NonReifiableTypes
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericsExample16NonReifiableTypes.class);

    public static void main(String[] args)
    {
        GenericsExample16NonReifiableTypes experiments = new GenericsExample16NonReifiableTypes();
        experiments.run();
    }

    private void run()
    {
        LOGGER.info("Erasure revision. Reifiable types, heap pollution.");

        List<String> longestList = findLongestBreakingStuff(List.of(""), List.of("aaa", "b", "c", "D"), List.of());

        // doesn't work - exception by design
        // String firstElement = longestList.get(0);
        // LOGGER.info("Result : {}", firstElement);

        longestList = findLongest(List.of(""), List.of("aaa", "b", "c", "D"), List.of());
        LOGGER.info("Longest list : {}", longestList);
    }

    List<String> findLongestBreakingStuff(List<String>... arrayOfLists)
    {
        int maxSize = 0;
        Object[] evilOne = arrayOfLists;
        List<String> longestList = null;
        for (int i = 0; i < arrayOfLists.length; i++)
        {
            if (arrayOfLists[i].size() > maxSize)
            {
                maxSize = arrayOfLists[i].size();
                evilOne[i] = List.of(42);
                longestList = arrayOfLists[i];

            }
        }

        return longestList;
    }

    // you can / should add this if you are confident your method doesn't break stuff while using arrays.
    // See implementation of List.of();
    @SafeVarargs
    final List<String> findLongest(List<String>... arrayOfLists)
    {
        int maxSize = 0;
        List<String> longestList = null;
        for (List<String> arrayOfList : arrayOfLists)
        {
            if (arrayOfList.size() > maxSize)
            {
                maxSize = arrayOfList.size();
                longestList = arrayOfList;
            }
        }

        return longestList;
    }

}
