package pl.jsystems.advancedjava.threads.solutions.s7bonusrefactoring;

import pl.jsystems.advancedjava.threads.solutions.s7bonusrefactoring.contents.MessageContent;
import pl.jsystems.advancedjava.threads.solutions.s7bonusrefactoring.message.Message;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageRepository<T extends MessageContent>
{
    List<Message<T>> findAll();

    Optional<Message<T>> findById(UUID id);

    void save(Message<T> message);
}
