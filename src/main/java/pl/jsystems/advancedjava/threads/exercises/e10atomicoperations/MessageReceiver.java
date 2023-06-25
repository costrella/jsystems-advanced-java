package pl.jsystems.advancedjava.threads.exercises.e10atomicoperations;

import pl.jsystems.advancedjava.threads.exercises.e10atomicoperations.contents.MessageContent;
import pl.jsystems.advancedjava.threads.exercises.e10atomicoperations.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
