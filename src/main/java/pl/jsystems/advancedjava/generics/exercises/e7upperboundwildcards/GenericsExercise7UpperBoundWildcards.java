package pl.jsystems.advancedjava.generics.exercises.e7upperboundwildcards;

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
    }
}
