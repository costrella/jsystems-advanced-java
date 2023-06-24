package pl.jsystems.advancedjava.threads.exercises.e2joiningthreads;

import pl.jsystems.advancedjava.threads.exercises.e2joiningthreads.contents.MessageContent;
import pl.jsystems.advancedjava.threads.exercises.e2joiningthreads.message.Message;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageRepository<T extends MessageContent>
{
    List<Message<T>> findAll();

    Optional<Message<T>> findById(UUID id);

    void save(Message<T> message);
}
