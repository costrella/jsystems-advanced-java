package pl.jsystems.advancedjava.reflection.solutions.s1basics;

import pl.jsystems.advancedjava.reflection.solutions.s1basics.contents.MessageContent;
import pl.jsystems.advancedjava.reflection.solutions.s1basics.message.Message;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageRepository<T extends MessageContent>
{
    List<Message<T>> findAll();

    Optional<Message<T>> findById(UUID id);

    void save(Message<T> message);
}
