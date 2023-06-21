package pl.jsystems.advancedjava.streams.exercises.e9flatmap;

import pl.jsystems.advancedjava.streams.exercises.e9flatmap.contents.MessageContent;
import pl.jsystems.advancedjava.streams.exercises.e9flatmap.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
