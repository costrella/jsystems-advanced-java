package pl.jsystems.advancedjava.streams.exercises.e2foreach;

import pl.jsystems.advancedjava.streams.exercises.e2foreach.contents.MessageContent;
import pl.jsystems.advancedjava.streams.exercises.e2foreach.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
