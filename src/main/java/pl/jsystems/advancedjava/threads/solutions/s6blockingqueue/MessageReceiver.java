package pl.jsystems.advancedjava.threads.solutions.s6blockingqueue;

import pl.jsystems.advancedjava.threads.solutions.s6blockingqueue.contents.MessageContent;
import pl.jsystems.advancedjava.threads.solutions.s6blockingqueue.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    Thread startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
