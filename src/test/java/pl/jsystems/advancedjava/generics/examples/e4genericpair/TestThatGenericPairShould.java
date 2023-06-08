package pl.jsystems.advancedjava.generics.examples.e4genericpair;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TestThatGenericPairShould
{
    @Test
    void allowToReadValuesAfterCreationEmpty()
    {
        // GIVEN
        String firstValue = "first value";
        Integer secondValue = 42;

        // WHEN
        Pair<String, Integer> pair = new Pair<>(firstValue, secondValue);

        // THEN
        assertThat(pair.getFirstValue()).isEqualTo(firstValue);
        assertThat(pair.getSecondValue()).isEqualTo(secondValue);
    }

    @Test
    void notAllowFirstValueToBeEmpty()
    {
        // GIVEN
        String firstValue = null;
        Integer secondValue = 42;

        // WHEN
        ThrowingCallable createPair = () -> new Pair<>(firstValue, secondValue);

        // THEN
        assertThatThrownBy(createPair)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("First value cannot be null.");
    }

    @Test
    void notAllowSecondValueToBeEmpty()
    {
        // GIVEN
        String firstValue = "first value";
        Integer secondValue = null;

        // WHEN
        ThrowingCallable createPair = () -> new Pair<>(firstValue, secondValue);

        // THEN
        assertThatThrownBy(createPair)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Second value cannot be null.");
    }
}
