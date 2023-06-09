package pl.jsystems.advancedjava.generics.solutions.s3genericmethods;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class TestThatMessageCreatorShould
{
    private final MessageCreator messageCreator = new MessageCreator();

    @Test
    void createAMessageBasedOnIdAndContent()
    {
        // GIVEN
        Long id = 42L;
        String content = "Test content";

        // WHEN
        Message<Long, String> message = messageCreator.createMessageUsing(id, content);

        // THEN
        assertThat(message.id()).isEqualTo(id);
        assertThat(message.content()).isEqualTo(content);
    }

    @Test
    void createAMessageWithUUIDAsLongContentOnly()
    {
        // GIVEN
        String content = "Some content";

        // WHEN
        Message<UUID, String> message = messageCreator.createMessageUsing(content);

        // THEN
        assertThat(message.id()).isNotNull();
        assertThat(message.content()).isEqualTo(content);
    }
}
