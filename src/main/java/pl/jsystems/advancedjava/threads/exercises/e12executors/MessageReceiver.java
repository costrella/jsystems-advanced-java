package pl.jsystems.advancedjava.threads.exercises.e12executors;

import pl.jsystems.advancedjava.threads.exercises.e12executors.contents.MessageContent;
import pl.jsystems.advancedjava.threads.exercises.e12executors.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
