package pl.jsystems.advancedjava.reflection.exercises.e4readfromfile;

import pl.jsystems.advancedjava.reflection.exercises.e4readfromfile.contents.MessageContent;
import pl.jsystems.advancedjava.reflection.exercises.e4readfromfile.message.Message;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageRepository<T extends MessageContent>
{
    List<Message<T>> findAll();

    Optional<Message<T>> findById(UUID id);

    void save(Message<T> message);
}
