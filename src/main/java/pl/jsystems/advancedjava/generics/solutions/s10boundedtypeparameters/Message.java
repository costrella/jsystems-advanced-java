package pl.jsystems.advancedjava.generics.solutions.s10boundedtypeparameters;

import java.time.Instant;
import java.util.UUID;

record Message<T extends MessageContent>(UUID id, T content, Instant sentAt)
{
    Message
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
    public String toString()
    {
        return "Message{" +
                "id=" + id +
                ", sentAt=" + sentAt +
                '}';
    }
}
