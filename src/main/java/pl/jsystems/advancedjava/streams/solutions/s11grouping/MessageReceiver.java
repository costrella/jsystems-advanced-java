package pl.jsystems.advancedjava.streams.solutions.s11grouping;

import pl.jsystems.advancedjava.streams.solutions.s11grouping.contents.MessageContent;
import pl.jsystems.advancedjava.streams.solutions.s11grouping.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}