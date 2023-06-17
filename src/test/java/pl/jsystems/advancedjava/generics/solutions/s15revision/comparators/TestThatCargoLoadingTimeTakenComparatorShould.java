package pl.jsystems.advancedjava.generics.solutions.s15revision.comparators;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;
import pl.jsystems.advancedjava.generics.solutions.s15revision.comparators.CargoLoadingTimeTakenComparator;
import pl.jsystems.advancedjava.generics.solutions.s15revision.contents.CargoLoadedMessageContent;
import pl.jsystems.advancedjava.generics.solutions.s15revision.contents.CargoUnloadedMessageContent;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TestThatCargoLoadingTimeTakenComparatorShould
{
    @Test
    void returnsPositiveNumberInCaseFirstContentIsBigger()
    {
        // GIVEN
        var cargoLoadingTimeTakenComparator = new CargoLoadingTimeTakenComparator();
        var cargoContent1 = new CargoUnloadedMessageContent(BigDecimal.ONE, 5000L);
        var cargoContent2 = new CargoLoadedMessageContent(BigDecimal.ONE, 4000L);

        // WHEN
        int result = cargoLoadingTimeTakenComparator.compare(cargoContent1, cargoContent2);

        // THEN
        assertThat(result).isPositive();
    }

    @Test
    void returnsNegativeNumberInCaseFirstContentIsLower()
    {
        // GIVEN
        var cargoLoadingTimeTakenComparator = new CargoLoadingTimeTakenComparator();
        var cargoContent1 = new CargoUnloadedMessageContent(BigDecimal.ONE, 3000L);
        var cargoContent2 = new CargoLoadedMessageContent(BigDecimal.ONE, 4000L);

        // WHEN
        int result = cargoLoadingTimeTakenComparator.compare(cargoContent1, cargoContent2);

        // THEN
        assertThat(result).isNegative();
    }

    @Test
    void returnsZeroInCaseFirstContentIsBigger()
    {
        // GIVEN
        var cargoLoadingTimeTakenComparator = new CargoLoadingTimeTakenComparator();
        var cargoContent1 = new CargoUnloadedMessageContent(BigDecimal.ONE, 3000L);
        var cargoContent2 = new CargoLoadedMessageContent(BigDecimal.ONE, 3000L);

        // WHEN
        int result = cargoLoadingTimeTakenComparator.compare(cargoContent1, cargoContent2);

        // THEN
        assertThat(result).isZero();
    }

    @Test
    void throwsExceptionIfFirstContentIsNull()
    {
        // GIVEN
        var cargoLoadingTimeTakenComparator = new CargoLoadingTimeTakenComparator();
        CargoUnloadedMessageContent cargoContent1 = null;
        var cargoContent2 = new CargoUnloadedMessageContent(BigDecimal.ONE, 3000L);

        // WHEN
        ThrowingCallable methodCall = () -> cargoLoadingTimeTakenComparator.compare(cargoContent1, cargoContent2);

        // THEN
        assertThatThrownBy(methodCall)
                .isInstanceOf(NullPointerException.class)
                .hasMessage("First cargo cannot be null.");
    }

    @Test
    void throwsExceptionIfSecondContentIsNull()
    {
        // GIVEN
        var cargoLoadingTimeTakenComparator = new CargoLoadingTimeTakenComparator();
        var cargoContent1 = new CargoUnloadedMessageContent(BigDecimal.ONE, 3000L);
        CargoUnloadedMessageContent cargoContent2 = null;

        // WHEN
        ThrowingCallable methodCall = () -> cargoLoadingTimeTakenComparator.compare(cargoContent1, cargoContent2);

        // THEN
        assertThatThrownBy(methodCall)
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Second cargo cannot be null.");
    }
}
