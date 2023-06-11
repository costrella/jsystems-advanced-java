package pl.jsystems.advancedjava.generics.examples.e11boundedtypeparameters;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TestThatBoxShould
{
    @Test
    void beCreatedEmpty()
    {
        // GIVEN

        // WHEN
        Box<Integer> box = new Box<>();

        // THEN
        assertThat(box.hasContent()).isFalse();
    }

    @Test
    void allowToStoreSomethingOfGivenType()
    {
        // GIVEN
        Box<Long> box = new Box<>();
        Long inputContent = 42L;

        // WHEN
        box.putInto(inputContent);

        // THEN
        assertThat(box.hasContent()).isTrue();
        assertThat(box.peekAtContent()).isEqualTo(inputContent);
    }

    @Test
    void allowToGetTheContentOfGivenTypeOut()
    {
        // GIVEN
        Box<Double> box = new Box<>();
        Double inputContent = 42d;

        // WHEN
        box.putInto(inputContent);
        Double content = box.getOutContent();

        // THEN
        assertThat(content).isEqualTo(inputContent);
        assertThat(box.hasContent()).isFalse();
        assertThat(box.peekAtContent()).isNull();
    }
}
