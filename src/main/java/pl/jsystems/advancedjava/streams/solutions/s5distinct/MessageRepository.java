package pl.jsystems.advancedjava.streams.solutions.s5distinct;

import pl.jsystems.advancedjava.streams.solutions.s5distinct.message.Message;
import pl.jsystems.advancedjava.streams.solutions.s5distinct.contents.MessageContent;

import java.util.List;
import java.util.UUID;

public interface MessageRepository<T extends MessageContent>
{
    List<Message<T>> findAll();

    Message<T> findById(UUID id);

    void save(Message<T> message);
}
