package pl.jsystems.advancedjava.streams.solutions.s2foreach;

import pl.jsystems.advancedjava.streams.solutions.s2foreach.contents.MessageContent;
import pl.jsystems.advancedjava.streams.solutions.s2foreach.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
