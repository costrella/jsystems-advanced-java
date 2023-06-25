package pl.jsystems.advancedjava.threads.exercises.e6blockingqueue;

import pl.jsystems.advancedjava.threads.exercises.e6blockingqueue.message.Message;
import pl.jsystems.advancedjava.threads.exercises.e6blockingqueue.contents.MessageContent;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
