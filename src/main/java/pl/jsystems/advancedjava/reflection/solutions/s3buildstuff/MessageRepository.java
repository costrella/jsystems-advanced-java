package pl.jsystems.advancedjava.reflection.solutions.s3buildstuff;

import pl.jsystems.advancedjava.reflection.solutions.s3buildstuff.contents.MessageContent;
import pl.jsystems.advancedjava.reflection.solutions.s3buildstuff.message.Message;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageRepository<T extends MessageContent>
{
    List<Message<T>> findAll();

    Optional<Message<T>> findById(UUID id);

    void save(Message<T> message);
}
