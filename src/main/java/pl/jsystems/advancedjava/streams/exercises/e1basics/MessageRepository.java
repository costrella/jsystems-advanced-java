package pl.jsystems.advancedjava.streams.exercises.e1basics;

import pl.jsystems.advancedjava.streams.exercises.e1basics.contents.MessageContent;
import pl.jsystems.advancedjava.streams.exercises.e1basics.message.Message;

import java.util.List;
import java.util.UUID;

public interface MessageRepository<T extends MessageContent>
{
    List<Message<T>> findAll();

    Message<T> findById(UUID id);

    void save(Message<T> message);
}