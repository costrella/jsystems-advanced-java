package pl.jsystems.advancedjava.threads.solutions.s8raceconditions;

import pl.jsystems.advancedjava.threads.solutions.s8raceconditions.contents.MessageContent;
import pl.jsystems.advancedjava.threads.solutions.s8raceconditions.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
