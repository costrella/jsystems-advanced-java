package pl.jsystems.advancedjava.threads.solutions.s10atomicoperations;

import pl.jsystems.advancedjava.threads.solutions.s10atomicoperations.contents.MessageContent;
import pl.jsystems.advancedjava.threads.solutions.s10atomicoperations.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
