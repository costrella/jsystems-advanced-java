package pl.jsystems.advancedjava.threads.exercises.e13future;

import pl.jsystems.advancedjava.threads.exercises.e13future.contents.MessageContent;
import pl.jsystems.advancedjava.threads.exercises.e13future.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
