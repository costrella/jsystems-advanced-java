package pl.jsystems.advancedjava.streams.solutions.s9flatmap;

import pl.jsystems.advancedjava.streams.solutions.s9flatmap.contents.MessageContent;
import pl.jsystems.advancedjava.streams.solutions.s9flatmap.message.Message;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageRepository<T extends MessageContent>
{
    List<Message<T>> findAll();

    Optional<Message<T>> findById(UUID id);

    void save(Message<T> message);
}
