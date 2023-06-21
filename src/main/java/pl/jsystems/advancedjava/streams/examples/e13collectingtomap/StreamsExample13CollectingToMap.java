package pl.jsystems.advancedjava.streams.examples.e13collectingtomap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class StreamsExample13CollectingToMap
{
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamsExample13CollectingToMap.class);

    public static void main(String[] args)
    {
        new StreamsExample13CollectingToMap().run();
    }

    private void run()
    {
        LOGGER.info("Lets group things...");
        Person person1 = new Person(1L, "name1");
        Person person2 = new Person(2L, "name2");
        Person person3 = new Person(3L, "name3");

        Map<Long, Person> personById = Stream.of(person1, person2, person3)
                // first argument is a function to create a key using Person object.
                // second argument is a function to createa a value using object.
                // Function.identity is same as person -> person.
                .collect(Collectors.toMap(person -> person.id, person -> person));
        LOGGER.info("Grouped people by their id: {}", personById);

        Payslip payslip1 = new Payslip(1L, BigDecimal.TEN, LocalDate.now().minusMonths(0));
        Payslip payslip2 = new Payslip(2L, BigDecimal.ONE, LocalDate.now().minusMonths(0));
        Payslip payslip3 = new Payslip(1L, BigDecimal.valueOf(20), LocalDate.now().minusMonths(1));
        Payslip payslip4 = new Payslip(2L, BigDecimal.valueOf(2), LocalDate.now().minusMonths(1));
        Payslip payslip5 = new Payslip(1L, BigDecimal.valueOf(30), LocalDate.now().minusMonths(2));
        Payslip payslip6 = new Payslip(2L, BigDecimal.valueOf(3), LocalDate.now().minusMonths(2));

        Map<Long, BigDecimal> payslipPerPerson = Stream.of(payslip1, payslip2, payslip3, payslip4, payslip5, payslip6).collect(
                // third argument is used in case of key duplicates. What shall we do then? Replace the value, sum, combine?
                // Here, we're adding values, but we can do (v1, v2) -> v1 - just always leaving the first value, or
                // (v1, v2) -> v2 - leaving the most recent one.
                Collectors.toMap(payslip -> payslip.personId, payslip -> payslip.salary, BigDecimal::add));
        LOGGER.info("Sum of payslips per person: {}", payslipPerPerson);
    }

    record Person(Long id, String name)
    {
    }

    record Payslip(Long personId, BigDecimal salary, LocalDate date)
    {
    }
}
