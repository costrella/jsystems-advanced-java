package pl.jsystems.advancedjava.streams.solutions.s2foreach;

import pl.jsystems.advancedjava.streams.solutions.s2foreach.contents.MessageContent;
import pl.jsystems.advancedjava.streams.solutions.s2foreach.message.Message;

import java.util.List;
import java.util.UUID;

public interface MessageRepository<T extends MessageContent>
{
    List<Message<T>> findAll();

    Message<T> findById(UUID id);

    void save(Message<T> message);
}
