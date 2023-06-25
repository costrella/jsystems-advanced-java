package pl.jsystems.advancedjava.threads.solutions.s11concurrenttypes;

import pl.jsystems.advancedjava.threads.solutions.s11concurrenttypes.contents.MessageContent;
import pl.jsystems.advancedjava.threads.solutions.s11concurrenttypes.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
