package pl.jsystems.advancedjava.threads.exercises.e9synchronizedstate;

import pl.jsystems.advancedjava.threads.exercises.e9synchronizedstate.message.Message;
import pl.jsystems.advancedjava.threads.exercises.e9synchronizedstate.contents.MessageContent;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
