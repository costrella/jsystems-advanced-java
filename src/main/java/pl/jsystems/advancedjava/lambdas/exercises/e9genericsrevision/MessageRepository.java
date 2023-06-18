package pl.jsystems.advancedjava.lambdas.exercises.e9genericsrevision;

import pl.jsystems.advancedjava.lambdas.exercises.e9genericsrevision.message.Message;
import pl.jsystems.advancedjava.lambdas.exercises.e9genericsrevision.contents.MessageContent;

import java.util.List;
import java.util.UUID;

public interface MessageRepository<T extends MessageContent>
{
    List<Message<T>> findAll();

    Message<T> findById(UUID id);

    void save(Message<T> message);
}
