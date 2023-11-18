package pl.jsystems.advancedjava.streams.solutions.s12partitioning;

import pl.jsystems.advancedjava.streams.solutions.s12partitioning.contents.MessageContent;
import pl.jsystems.advancedjava.streams.solutions.s12partitioning.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}