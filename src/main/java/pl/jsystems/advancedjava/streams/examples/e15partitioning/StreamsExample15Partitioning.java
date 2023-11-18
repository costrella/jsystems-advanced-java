package pl.jsystems.advancedjava.streams.examples.e15partitioning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class StreamsExample15Partitioning
{
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamsExample15Partitioning.class);

    public static void main(String[] args)
    {
        new StreamsExample15Partitioning().run();
    }

    private void run()
    {
        LOGGER.info("True or false?");

        Payslip payslip1 = new Payslip(1L, BigDecimal.TEN, LocalDate.now().minusMonths(0));
        Payslip payslip2 = new Payslip(2L, BigDecimal.ONE, LocalDate.now().minusMonths(0));
        Payslip payslip3 = new Payslip(1L, BigDecimal.valueOf(20), LocalDate.now().minusMonths(1));
        Payslip payslip4 = new Payslip(2L, BigDecimal.valueOf(2), LocalDate.now().minusMonths(1));
        Payslip payslip5 = new Payslip(1L, BigDecimal.valueOf(30), LocalDate.now().minusMonths(2));
        Payslip payslip6 = new Payslip(2L, BigDecimal.valueOf(3), LocalDate.now().minusMonths(2));

        Map<Boolean, List<Payslip>> payslipsPartitionedByEmployeeNumber = Stream.of(payslip1, payslip2, payslip3, payslip4, payslip5, payslip6).collect(
                Collectors.partitioningBy(payslip -> payslip.personId < 2));
        LOGGER.info("List of payslips for the first employee: {}", payslipsPartitionedByEmployeeNumber.get(true));
        LOGGER.info("List of payslips for the second employee: {}", payslipsPartitionedByEmployeeNumber.get(false));

        Map<Boolean, Set<Payslip>> payslipsSetPartitionedByEmployeeNumber = Stream.of(payslip1, payslip2, payslip3, payslip4, payslip5, payslip6).collect(
                Collectors.partitioningBy(payslip -> payslip.personId < 2, Collectors.toSet()));
        LOGGER.info("List of payslips for the first employee: {}", payslipsSetPartitionedByEmployeeNumber.get(true));
        LOGGER.info("List of payslips for the second employee: {}", payslipsSetPartitionedByEmployeeNumber.get(false));

        Map<Boolean, BigDecimal> payslipsTotalSumEmployeeNumber = Stream.of(payslip1, payslip2, payslip3, payslip4, payslip5, payslip6).collect(
                Collectors.partitioningBy(payslip -> payslip.personId < 2, Collectors.mapping(payslip -> payslip.salary, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
        LOGGER.info("Total sum of payslips for the first employee: {}", payslipsTotalSumEmployeeNumber.get(true));
        LOGGER.info("Total sum payslips for the second employee: {}", payslipsTotalSumEmployeeNumber.get(false));
    }

    record Payslip(Long personId, BigDecimal salary, LocalDate date)
    {
    }
}