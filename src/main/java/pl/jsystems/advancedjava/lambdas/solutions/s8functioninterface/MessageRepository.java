package pl.jsystems.advancedjava.lambdas.solutions.s8functioninterface;

import pl.jsystems.advancedjava.lambdas.solutions.s8functioninterface.contents.MessageContent;
import pl.jsystems.advancedjava.lambdas.solutions.s8functioninterface.message.Message;

import java.util.List;
import java.util.UUID;

public interface MessageRepository<T extends MessageContent>
{
    List<Message<T>> findAll();

    Message<T> findById(UUID id);

    void save(Message<T> message);
}
