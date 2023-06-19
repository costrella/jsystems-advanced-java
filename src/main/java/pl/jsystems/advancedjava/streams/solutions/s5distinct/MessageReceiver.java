package pl.jsystems.advancedjava.streams.solutions.s5distinct;

import pl.jsystems.advancedjava.streams.solutions.s5distinct.contents.MessageContent;
import pl.jsystems.advancedjava.streams.solutions.s5distinct.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
