package pl.jsystems.advancedjava.lambdas.exercises.e4methodreference;

import pl.jsystems.advancedjava.lambdas.exercises.e4methodreference.message.Message;
import pl.jsystems.advancedjava.lambdas.exercises.e4methodreference.contents.MessageContent;

import java.util.List;
import java.util.UUID;

public interface MessageRepository<T extends MessageContent>
{
    List<Message<T>> findAll();

    Message<T> findById(UUID id);

    void save(Message<T> message);
}
