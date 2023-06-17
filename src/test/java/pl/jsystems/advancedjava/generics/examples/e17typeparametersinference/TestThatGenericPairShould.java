package pl.jsystems.advancedjava.generics.examples.e17typeparametersinference;

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
        Number firstValue = 1;
        Integer secondValue = 42;

        // WHEN
        Pair<Number, Integer> pair = Pair.createPair(firstValue, secondValue);

        // THEN
        assertThat(pair.firstValue()).isEqualTo(firstValue);
        assertThat(pair.secondValue()).isEqualTo(secondValue);
    }

    @Test
    void notAllowFirstValueToBeEmpty()
    {
        // GIVEN
        Double firstValue = null;
        Integer secondValue = 42;

        // WHEN
        ThrowingCallable createPair = () -> Pair.createPair(firstValue, secondValue);

        // THEN
        assertThatThrownBy(createPair)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("First value cannot be null.");
    }

    @Test
    void notAllowSecondValueToBeEmpty()
    {
        // GIVEN
        Number firstValue = 2L;
        Integer secondValue = null;

        // WHEN
        ThrowingCallable createPair = () -> Pair.createPair(firstValue, secondValue);

        // THEN
        assertThatThrownBy(createPair)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Second value cannot be null.");
    }
}
