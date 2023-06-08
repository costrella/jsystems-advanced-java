package pl.jsystems.advancedjava.generics.solutions.s1genericmessage;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TestThatGenericMessageShould
{
    @Test
    void allowToGetContentAfterCreation()
    {
        // GIVEN
        String content = "Test content";

        // WHEN
        Message<String> message = new Message<>(content);

        // THEN
        assertThat(message.getContent()).isEqualTo(content);
    }

    @Test
    void notAllowForNullContent()
    {
        // GIVEN
        String content = null;

        // WHEN
        // We will explain that during 'lambdas' part of the course.
        ThrowingCallable callable = () -> new Message<>(content);

        // THEN
        assertThatThrownBy(callable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Content cannot be null.");
    }
}
