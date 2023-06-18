package pl.jsystems.advancedjava.streams.examples.e6sorting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

class StreamsExample6Sorting
{
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamsExample6Sorting.class);

    public static void main(String[] args)
    {
        StreamsExample6Sorting app = new StreamsExample6Sorting();
        app.run();
    }

    private void run()
    {
        List<String> strings = List.of("3", "2", "1", "3", "4", "-1", "-42", "42", "13");
        LOGGER.info("Created a list of Strings: {} - creating stream out of them", strings);

        List<Integer> integers = strings.stream()
                .map(Integer::valueOf)
                .sorted()
                .toList();

        LOGGER.info("Sorted list of integers: {}", integers);

        List<Integer> reversedIntegers = strings.stream()
                .map(Integer::valueOf)
                .sorted(Comparator.reverseOrder())
                .toList();

        LOGGER.info("Reverse sorted list of integers: {}", reversedIntegers);

        List<Integer> strangelySortedIntegers = strings.stream()
                .map(Integer::valueOf)
                .sorted(new EvenBeforeOddIntegerComparator())
                .toList();

        LOGGER.info("Strangely sorted list of integers: {}", strangelySortedIntegers);

        List<Integer> sortedByNumberOfDigitsAndSigns = strings.stream()
                .sorted((i1, i2) -> (i2.length() - i1.length()) != 0 ? i2.length() - i1.length() : i2.compareTo(i1))
                // or
                //.sorted(Comparator.comparingInt(String::length))
                .map(Integer::valueOf)
                .toList();

        LOGGER.info("Strangely sorted list of integers: {}", sortedByNumberOfDigitsAndSigns);

        // this should fail - 'sorted' uses natural ordering,
        // which means it requires objects being sorted to implement Comparable.
        var list = Stream.of(new Object(), new Object()).sorted().toList();
        LOGGER.info("Sorting works for Objects? {}", list);
    }

    static class EvenBeforeOddIntegerComparator implements Comparator<Integer>
    {

        @Override
        public int compare(Integer o1, Integer o2)
        {
            // return  (((o1 % 2 == 0) && (o2 % 2 == 0)) || ((Math.abs(o1 % 2) == 1) && (Math.abs(o2 % 2) == 1))) ? o1.compareTo(o2) :
            //        ((o1 % 2 == 0) ? -1 : 1);
            if (bothEven(o1, o2) || bothOdd(o1, o2))
            {
                return o1.compareTo(o2);
            }

            if (isEven(o1))
            {
                return -1;
            }

            return 1;
        }

        private boolean bothEven(Integer int1, Integer int2)
        {
            return isEven(int1) && isEven(int2);
        }

        private boolean bothOdd(Integer int1, Integer int2)
        {
            return !isEven(int1) && !isEven(int2);
        }

        private boolean isEven(Integer value)
        {
            return value % 2 == 0;
        }
    }
}
