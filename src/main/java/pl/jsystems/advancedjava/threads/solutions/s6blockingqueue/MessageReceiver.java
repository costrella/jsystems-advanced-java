package pl.jsystems.advancedjava.threads.solutions.s6blockingqueue;

import pl.jsystems.advancedjava.threads.solutions.s6blockingqueue.message.Message;
import pl.jsystems.advancedjava.threads.solutions.s6blockingqueue.contents.MessageContent;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
