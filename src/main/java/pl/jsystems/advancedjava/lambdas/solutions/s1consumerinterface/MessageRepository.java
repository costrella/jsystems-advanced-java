package pl.jsystems.advancedjava.lambdas.solutions.s1consumerinterface;

import pl.jsystems.advancedjava.lambdas.solutions.s1consumerinterface.contents.MessageContent;
import pl.jsystems.advancedjava.lambdas.solutions.s1consumerinterface.message.Message;

import java.util.List;
import java.util.UUID;

public interface MessageRepository<T extends MessageContent>
{
    List<Message<T>> findAll();

    Message<T> findById(UUID id);

    void save(Message<T> message);
}
