package pl.jsystems.advancedjava.streams.exercises.e7minmaxpeek;

import pl.jsystems.advancedjava.streams.exercises.e7minmaxpeek.contents.MessageContent;
import pl.jsystems.advancedjava.streams.exercises.e7minmaxpeek.message.Message;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageRepository<T extends MessageContent>
{
    List<Message<T>> findAll();

    Optional<Message<T>> findById(UUID id);

    void save(Message<T> message);
}