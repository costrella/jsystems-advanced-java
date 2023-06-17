package pl.jsystems.advancedjava.generics.solutions.s13extendinggenericclasses;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TestThatGenericMessageShould
{
    @Test
    void allowToGetContentAndIdAfterCreation()
    {
        // GIVEN
        UUID id = UUID.randomUUID();
        GPSTrackingMessageContent content = new GPSTrackingMessageContent();
        Instant sentAt = Instant.now();

        // WHEN
        Message<GPSTrackingMessageContent> message = new Message<>(id, content, sentAt);

        // THEN
        assertThat(message.id()).isEqualTo(id);
        assertThat(message.content()).isEqualTo(content);
    }

    @Test
    void notAllowForNullId()
    {
        // GIVEN
        UUID id = null;
        CargoUnloadedMessageContent content = new CargoUnloadedMessageContent();
        Instant sentAt = Instant.now();

        // WHEN
        // We will explain that during 'lambdas' part of the course.
        ThrowingCallable callable = () -> new Message<>(id, content, sentAt);

        // THEN
        assertThatThrownBy(callable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("'Id' cannot be null.");
    }

    @Test
    void notAllowForNullContent()
    {
        // GIVEN
        UUID id = UUID.randomUUID();
        CargoReadyForLoadingMessageContent content = null;
        Instant sentAt = Instant.now();

        // WHEN
        // We will explain that during 'lambdas' part of the course.
        ThrowingCallable callable = () -> new Message<>(id, content, sentAt);

        // THEN
        assertThatThrownBy(callable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("'Content' cannot be null.");
    }

    @Test
    void notAllowForNullInstant()
    {
        // GIVEN
        UUID id = UUID.randomUUID();
        CargoLoadedMessageContent content = new CargoLoadedMessageContent();
        Instant sentAt = null;

        // WHEN
        // We will explain that during 'lambdas' part of the course.
        ThrowingCallable callable = () -> new Message<>(id, content, sentAt);

        // THEN
        assertThatThrownBy(callable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("'Sent at' cannot be null.");
    }
}