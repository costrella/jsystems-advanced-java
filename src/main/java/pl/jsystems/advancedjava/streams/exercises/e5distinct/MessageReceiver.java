package pl.jsystems.advancedjava.streams.exercises.e5distinct;

import pl.jsystems.advancedjava.streams.exercises.e5distinct.contents.MessageContent;
import pl.jsystems.advancedjava.streams.exercises.e5distinct.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
