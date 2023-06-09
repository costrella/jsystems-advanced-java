package pl.jsystems.advancedjava.generics.solutions.s5nestedgenerics;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TestThatGenericBoxShould
{
    @Test
    void beCreatedEmpty()
    {
        // GIVEN

        // WHEN
        Box<String> box = new Box<>();

        // THEN
        assertThat(box.hasContent()).isFalse();
    }

    @Test
    void allowToStoreSomethingOfGivenType()
    {
        // GIVEN
        Box<String> box = new Box<>();
        String inputContent = "Some content";

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
        Box<String> box = new Box<>();
        String inputContent = "Some content";

        // WHEN
        box.putInto(inputContent);
        String content = box.getOutContent();

        // THEN
        assertThat(content).isEqualTo(inputContent);
        assertThat(box.hasContent()).isFalse();
        assertThat(box.peekAtContent()).isNull();
    }
}
