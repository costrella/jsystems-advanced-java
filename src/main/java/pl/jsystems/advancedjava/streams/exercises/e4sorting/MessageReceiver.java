package pl.jsystems.advancedjava.streams.exercises.e4sorting;

import pl.jsystems.advancedjava.streams.exercises.e4sorting.contents.MessageContent;
import pl.jsystems.advancedjava.streams.exercises.e4sorting.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
