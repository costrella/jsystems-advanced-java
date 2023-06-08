package pl.jsystems.advancedjava.generics.solutions.s2genericmessageid;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TestThatGenericMessageShould
{
    @Test
    void allowToGetContentAndIdAfterCreation()
    {
        // GIVEN
        Long id = 42L;
        String content = "Test content";

        // WHEN
        Message<Long, String> message = new Message<>(id, content);

        // THEN
        assertThat(message.id()).isEqualTo(id);
        assertThat(message.content()).isEqualTo(content);
    }

    @Test
    void notAllowForNullContent()
    {
        // GIVEN
        Long id = 42L;
        String content = null;

        // WHEN
        // We will explain that during 'lambdas' part of the course.
        ThrowingCallable callable = () -> new Message<>(id, content);

        // THEN
        assertThatThrownBy(callable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Content cannot be null.");
    }

    @Test
    void notAllowForNullId()
    {
        // GIVEN
        Long id = null;
        String content = "some content";

        // WHEN
        // We will explain that during 'lambdas' part of the course.
        ThrowingCallable callable = () -> new Message<>(id, content);

        // THEN
        assertThatThrownBy(callable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Id cannot be null.");
    }
}
