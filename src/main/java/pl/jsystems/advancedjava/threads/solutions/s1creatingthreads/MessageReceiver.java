package pl.jsystems.advancedjava.threads.solutions.s1creatingthreads;

import pl.jsystems.advancedjava.threads.solutions.s1creatingthreads.message.Message;
import pl.jsystems.advancedjava.threads.solutions.s1creatingthreads.contents.MessageContent;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
