package pl.jsystems.advancedjava.streams.exercises.e8concatandreduce;

import pl.jsystems.advancedjava.streams.exercises.e8concatandreduce.message.Message;
import pl.jsystems.advancedjava.streams.exercises.e8concatandreduce.contents.MessageContent;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageRepository<T extends MessageContent>
{
    List<Message<T>> findAll();

    Optional<Message<T>> findById(UUID id);

    void save(Message<T> message);
}
