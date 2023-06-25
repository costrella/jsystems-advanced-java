package pl.jsystems.advancedjava.threads.exercises.e8raceconditions;

import pl.jsystems.advancedjava.threads.exercises.e8raceconditions.contents.MessageContent;
import pl.jsystems.advancedjava.threads.exercises.e8raceconditions.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
