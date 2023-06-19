package pl.jsystems.advancedjava.streams.exercises.e6optional;

import pl.jsystems.advancedjava.streams.exercises.e6optional.message.Message;
import pl.jsystems.advancedjava.streams.exercises.e6optional.contents.MessageContent;

import java.util.List;
import java.util.UUID;

public interface MessageRepository<T extends MessageContent>
{
    List<Message<T>> findAll();

    Message<T> findById(UUID id);

    void save(Message<T> message);
}
