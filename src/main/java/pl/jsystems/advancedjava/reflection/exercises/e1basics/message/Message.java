package pl.jsystems.advancedjava.reflection.exercises.e1basics.message;

import pl.jsystems.advancedjava.reflection.exercises.e1basics.contents.MessageContent;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Message<T extends MessageContent> implements Comparable<Message<T>>
{
    private final UUID id;
    private final T content;
    private final Instant sentAt;

    public Message(UUID id, T content, Instant sentAt)
    {
        validate(id, "Id");
        validate(content, "Content");
        validate(sentAt, "Sent at");
        this.id = id;
        this.content = content;
        this.sentAt = sentAt;
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

    public UUID id()
    {
        return id;
    }

    public T content()
    {
        return content;
    }

    public Instant sentAt()
    {
        return sentAt;
    }

}
