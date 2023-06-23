package pl.jsystems.advancedjava.streams.solutions.s11grouping.message;

import pl.jsystems.advancedjava.streams.solutions.s11grouping.contents.MessageContent;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public record Message<T extends MessageContent>(UUID id, T content, Instant sentAt) implements Comparable<Message<T>>
{
    public Message
    {
        validate(id, "Id");
        validate(content, "Content");
        validate(sentAt, "Sent at");
    }

    private void validate(Object content, String fieldName)
    {
        if (content == null)
        {
            throw new IllegalArgumentException("'" + fieldName + "' cannot be null.");
        }
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message<?> message = (Message<?>) o;
        return id.equals(message.id);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }

    @Override
    public String toString()
    {
        return "Message{" +
                "id=" + id +
                ", sentAt=" + sentAt +
                '}';
    }

    @Override
    public int compareTo(Message<T> o)
    {
        return sentAt.compareTo(o.sentAt);
    }
}
