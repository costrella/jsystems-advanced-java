package pl.jsystems.advancedjava.reflection.exercises.e2breakingstuff;

import pl.jsystems.advancedjava.reflection.exercises.e2breakingstuff.contents.MessageContent;
import pl.jsystems.advancedjava.reflection.exercises.e2breakingstuff.message.Message;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageRepository<T extends MessageContent>
{
    List<Message<T>> findAll();

    Optional<Message<T>> findById(UUID id);

    void save(Message<T> message);
}
