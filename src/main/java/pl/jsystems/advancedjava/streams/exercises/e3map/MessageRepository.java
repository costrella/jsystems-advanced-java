package pl.jsystems.advancedjava.streams.exercises.e3map;

import pl.jsystems.advancedjava.streams.exercises.e3map.contents.MessageContent;
import pl.jsystems.advancedjava.streams.exercises.e3map.message.Message;

import java.util.List;
import java.util.UUID;

public interface MessageRepository<T extends MessageContent>
{
    List<Message<T>> findAll();

    Message<T> findById(UUID id);

    void save(Message<T> message);
}
