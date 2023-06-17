package pl.jsystems.advancedjava.lambdas.solutions.s2anonymousclasses;

import pl.jsystems.advancedjava.lambdas.solutions.s2anonymousclasses.contents.MessageContent;
import pl.jsystems.advancedjava.lambdas.solutions.s2anonymousclasses.message.Message;

import java.util.List;
import java.util.UUID;

public interface MessageRepository<T extends MessageContent>
{
    List<Message<T>> findAll();

    Message<T> findById(UUID id);

    void save(Message<T> message);
}
