package pl.jsystems.advancedjava.streams.solutions.s1basics;

import pl.jsystems.advancedjava.streams.solutions.s1basics.message.Message;
import pl.jsystems.advancedjava.streams.solutions.s1basics.contents.MessageContent;

import java.util.List;
import java.util.UUID;

public interface MessageRepository<T extends MessageContent>
{
    List<Message<T>> findAll();

    Message<T> findById(UUID id);

    void save(Message<T> message);
}
