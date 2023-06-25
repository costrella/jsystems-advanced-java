package pl.jsystems.advancedjava.threads.solutions.s8raceconditions;

import pl.jsystems.advancedjava.threads.solutions.s8raceconditions.contents.MessageContent;
import pl.jsystems.advancedjava.threads.solutions.s8raceconditions.message.Message;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageRepository<T extends MessageContent>
{
    List<Message<T>> findAll();

    Optional<Message<T>> findById(UUID id);

    void save(Message<T> message);
}
