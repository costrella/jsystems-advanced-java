package pl.jsystems.advancedjava.generics.solutions.s15revision;

import pl.jsystems.advancedjava.generics.solutions.s15revision.contents.MessageContent;
import pl.jsystems.advancedjava.generics.solutions.s15revision.message.Message;

import java.util.List;
import java.util.UUID;

public interface MessageRepository<T extends MessageContent>
{
    List<Message<T>> findAll();

    Message<T> findById(UUID id);

    void save(Message<T> message);
}
