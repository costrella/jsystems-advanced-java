package pl.jsystems.advancedjava.lambdas.exercises.e3consumerlambda;

import pl.jsystems.advancedjava.lambdas.exercises.e3consumerlambda.contents.MessageContent;
import pl.jsystems.advancedjava.lambdas.exercises.e3consumerlambda.message.Message;

import java.util.List;
import java.util.UUID;

public interface MessageRepository<T extends MessageContent>
{
    List<Message<T>> findAll();

    Message<T> findById(UUID id);

    void save(Message<T> message);
}