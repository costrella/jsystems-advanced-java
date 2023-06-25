package pl.jsystems.advancedjava.threads.exercises.e11concurrenttypes;

import pl.jsystems.advancedjava.threads.exercises.e11concurrenttypes.contents.MessageContent;
import pl.jsystems.advancedjava.threads.exercises.e11concurrenttypes.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
