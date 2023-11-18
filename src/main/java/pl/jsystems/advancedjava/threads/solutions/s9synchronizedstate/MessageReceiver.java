package pl.jsystems.advancedjava.threads.solutions.s9synchronizedstate;

import pl.jsystems.advancedjava.threads.solutions.s9synchronizedstate.contents.MessageContent;
import pl.jsystems.advancedjava.threads.solutions.s9synchronizedstate.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}