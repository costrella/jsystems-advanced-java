package pl.jsystems.advancedjava.threads.solutions.s12executors;

import pl.jsystems.advancedjava.threads.solutions.s12executors.contents.MessageContent;
import pl.jsystems.advancedjava.threads.solutions.s12executors.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
