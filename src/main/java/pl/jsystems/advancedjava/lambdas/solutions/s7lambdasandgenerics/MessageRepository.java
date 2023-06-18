package pl.jsystems.advancedjava.lambdas.solutions.s7lambdasandgenerics;

import pl.jsystems.advancedjava.lambdas.solutions.s7lambdasandgenerics.contents.MessageContent;
import pl.jsystems.advancedjava.lambdas.solutions.s7lambdasandgenerics.message.Message;

import java.util.List;
import java.util.UUID;

public interface MessageRepository<T extends MessageContent>
{
    List<Message<T>> findAll();

    Message<T> findById(UUID id);

    void save(Message<T> message);
}
