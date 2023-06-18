package pl.jsystems.advancedjava.streams.solutions.s1basics;

import pl.jsystems.advancedjava.streams.solutions.s1basics.contents.MessageContent;
import pl.jsystems.advancedjava.streams.solutions.s1basics.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
