package pl.jsystems.advancedjava.streams.examples.e14grouping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class StreamsExample14Grouping
{
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamsExample14Grouping.class);

    public static void main(String[] args)
    {
        new StreamsExample14Grouping().run();
    }

    private void run()
    {
        LOGGER.info("Lets group things...");

        Payslip payslip1 = new Payslip(1L, BigDecimal.TEN, LocalDate.now().minusMonths(0));
        Payslip payslip2 = new Payslip(2L, BigDecimal.ONE, LocalDate.now().minusMonths(0));
        Payslip payslip3 = new Payslip(1L, BigDecimal.valueOf(20), LocalDate.now().minusMonths(1));
        Payslip payslip4 = new Payslip(2L, BigDecimal.valueOf(2), LocalDate.now().minusMonths(1));
        Payslip payslip5 = new Payslip(1L, BigDecimal.valueOf(30), LocalDate.now().minusMonths(2));
        Payslip payslip6 = new Payslip(2L, BigDecimal.valueOf(3), LocalDate.now().minusMonths(2));

        Map<Long, List<Payslip>> payslipPerPerson = Stream.of(payslip1, payslip2, payslip3, payslip4, payslip5, payslip6).collect(
                Collectors.groupingBy(payslip -> payslip.personId));
        LOGGER.info("List of payslips per person: {}", payslipPerPerson);

        Map<Long, Set<Payslip>> uniquePayslipPerPerson = Stream.of(payslip1, payslip2, payslip3, payslip4, payslip5, payslip6).collect(
                Collectors.groupingBy(payslip -> payslip.personId, Collectors.toSet()));
        LOGGER.info("Set of payslips per person: {}", uniquePayslipPerPerson);

        Map<Long, Optional<Payslip>> maxPayslipPerPerson = Stream.of(payslip1, payslip2, payslip3, payslip4, payslip5, payslip6).collect(
                Collectors.groupingBy(payslip -> payslip.personId, Collectors.maxBy(Comparator.comparing(p -> p.salary))));
        LOGGER.info("Max value of payslips per person: {}", maxPayslipPerPerson);

        Map<Long, Optional<Payslip>> minPayslipPerPerson = Stream.of(payslip1, payslip2, payslip3, payslip4, payslip5, payslip6).collect(
                Collectors.groupingBy(payslip -> payslip.personId, Collectors.minBy(Comparator.comparing(p -> p.salary))));
        LOGGER.info("Min value of payslips per person: {}", minPayslipPerPerson);

        Map<Long, Double> averagePayslipPerPerson = Stream.of(payslip1, payslip2, payslip3, payslip4, payslip5, payslip6).collect(
                Collectors.groupingBy(payslip -> payslip.personId, Collectors.averagingDouble(payslip -> payslip.salary.doubleValue())));
        LOGGER.info("Average value of payslips per person: {}", averagePayslipPerPerson);

        Map<Long, Double> summingPayslipPerPerson = Stream.of(payslip1, payslip2, payslip3, payslip4, payslip5, payslip6).collect(
                Collectors.groupingBy(payslip -> payslip.personId, Collectors.summingDouble(payslip -> payslip.salary.doubleValue())));
        LOGGER.info("Sum of payslips per person: {}", summingPayslipPerPerson);

        Map<Long, BigDecimal> summingByReducingPayslipPerPerson = Stream.of(payslip1, payslip2, payslip3, payslip4, payslip5, payslip6).collect(
                Collectors.groupingBy(payslip -> payslip.personId, Collectors.reducing(BigDecimal.ZERO, p1 -> p1.salary, BigDecimal::add)));
        LOGGER.info("Sum of payslips per person (by reducing): {}", summingByReducingPayslipPerPerson);

        Map<LocalDate, Long> numberOfPayslipsPerMonth = Stream.of(payslip1, payslip2, payslip3, payslip4, payslip5, payslip6).collect(
                Collectors.groupingBy(payslip -> payslip.date, Collectors.counting())
        );
        LOGGER.info("Number of payslips per month: {}", numberOfPayslipsPerMonth);

        Map<LocalDate, Double> sumOfPayslipsPerMonth = Stream.of(payslip1, payslip2, payslip3, payslip4, payslip5, payslip6).collect(
                Collectors.groupingBy(payslip -> payslip.date, Collectors.mapping(payslip -> payslip.salary, Collectors.summingDouble(BigDecimal::doubleValue)))
        );
        LOGGER.info("Sum of payslips per month: {}", sumOfPayslipsPerMonth);
    }

    record Payslip(Long personId, BigDecimal salary, LocalDate date)
    {
    }
}
