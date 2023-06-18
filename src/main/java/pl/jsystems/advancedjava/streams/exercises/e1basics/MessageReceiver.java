package pl.jsystems.advancedjava.streams.exercises.e1basics;

import pl.jsystems.advancedjava.streams.exercises.e1basics.contents.MessageContent;
import pl.jsystems.advancedjava.streams.exercises.e1basics.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
