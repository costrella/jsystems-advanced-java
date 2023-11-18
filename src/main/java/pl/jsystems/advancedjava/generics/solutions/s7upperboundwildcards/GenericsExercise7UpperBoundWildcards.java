package pl.jsystems.advancedjava.generics.solutions.s7upperboundwildcards;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

class GenericsExercise7UpperBoundWildcards
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericsExercise7UpperBoundWildcards.class);

    public static void main(String[] args)
    {
        new GenericsExercise7UpperBoundWildcards().run();
    }

    private void run()
    {
        LOGGER.info("Let's sum some numbers!");
        List<Number> numbers = List.of(3, 6L, 12d, 21f);
        List<Integer> integers = List.of(-15, 1, 2, 3, 4, 5, 6, 36);
        List<Long> longs = List.of(1L, 2L, 3L, 4L, -10L, -40L, 82L);
        List<BigInteger> bigIntegers = List.of(BigInteger.TEN, BigInteger.TWO, BigInteger.TEN, BigInteger.TEN, BigInteger.TEN);
        List<Float> floats = List.of(21f, 10f, -10f, 100f, -100f, 21f);
        List<Double> doubles = List.of(42d, -42d, -42d, 42d, 42d);
        List<BigDecimal> bigDecimals = List.of(BigDecimal.valueOf(42));

        LOGGER.info("Sum of numbers: {}", sumOf(numbers));
        LOGGER.info("Sum of integers: {}", sumOf(integers));
        LOGGER.info("Sum of longs: {}", sumOf(longs));
        LOGGER.info("Sum of bigIntegers: {}", sumOf(bigIntegers));
        LOGGER.info("Sum of floats: {}", sumOf(floats));
        LOGGER.info("Sum of doubles: {}", sumOf(doubles));
        LOGGER.info("Sum of bigDecimals: {}", sumOf(bigDecimals));

        LOGGER.info("Min of numbers: {}", minOf(numbers));
        LOGGER.info("Min of integers: {}", minOf(integers));
        LOGGER.info("Min of longs: {}", minOf(longs));
        LOGGER.info("Min of bigIntegers: {}", minOf(bigIntegers));
        LOGGER.info("Min of floats: {}", minOf(floats));
        LOGGER.info("Min of doubles: {}", minOf(doubles));
        LOGGER.info("Min of bigDecimals: {}", minOf(bigDecimals));
    }

    private double sumOf(List<? extends Number> numbers)
    {
        double sum = 0;
        for (Number number : numbers)
        {
            sum += number.doubleValue();
        }
        return sum;
    }

    private double minOf(List<? extends Number> numbers)
    {
        double min = Double.MAX_VALUE;
        for (Number number : numbers)
        {
            if (number.doubleValue() < min)
            {
                min = number.doubleValue();
            }
        }
        return min;
    }
}