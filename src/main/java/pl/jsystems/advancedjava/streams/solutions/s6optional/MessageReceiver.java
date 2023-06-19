package pl.jsystems.advancedjava.streams.solutions.s6optional;

import pl.jsystems.advancedjava.streams.solutions.s6optional.contents.MessageContent;
import pl.jsystems.advancedjava.streams.solutions.s6optional.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
