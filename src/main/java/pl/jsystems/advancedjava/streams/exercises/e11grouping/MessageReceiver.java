package pl.jsystems.advancedjava.streams.exercises.e11grouping;

import pl.jsystems.advancedjava.streams.exercises.e11grouping.message.Message;
import pl.jsystems.advancedjava.streams.exercises.e11grouping.contents.MessageContent;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
