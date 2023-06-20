package pl.jsystems.advancedjava.streams.examples.e10concatandreduce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Stream;

class StreamsExample10ConcatAndReduce
{
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamsExample10ConcatAndReduce.class);

    public static void main(String[] args)
    {
        new StreamsExample10ConcatAndReduce().run();
    }

    private void run()
    {
        List<Integer> integers = List.of(1, 2, 4, 42, 4, 2, 1);
        ;
        LOGGER.info("Original list1: {}", integers);
        List<Integer> moreIntegers = List.of(-1, -2, -4, -4, -2, -1);
        LOGGER.info("Original list2: {}", moreIntegers);

        int sum = Stream.concat(integers.stream(), moreIntegers.stream()).reduce(0, Integer::sum);
        LOGGER.info("Sum is: " + sum);

        var employee1 = new Employee("M1", 1000L);
        var employee2 = new Employee("M2", 2000L);
        var employee3 = new Employee("M3", 3000L);
        var employee4 = new Employee("M4", 4000L);


        Long salarySum = Stream.of(employee1, employee2, employee3, employee4)
                // will be discussed in more detail in threads part
                .parallel()
                // Long::sum is required because if the stream is parallel,
                // we need to sum the parts from different threads at the end.
                .reduce(0L, (partial, employee) -> partial + employee.salary, Long::sum);

        LOGGER.info("Salary sum: {}", salarySum);
    }

    record Employee(String name, Long salary)
    {
    }
}
