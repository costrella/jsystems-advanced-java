package pl.jsystems.advancedjava.streams.exercises.e12partitioning;

import pl.jsystems.advancedjava.streams.exercises.e12partitioning.message.Message;
import pl.jsystems.advancedjava.streams.exercises.e12partitioning.contents.MessageContent;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageRepository<T extends MessageContent>
{
    List<Message<T>> findAll();

    Optional<Message<T>> findById(UUID id);

    void save(Message<T> message);
}
