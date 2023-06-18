package pl.jsystems.advancedjava.streams.exercises.e3map;

import pl.jsystems.advancedjava.streams.exercises.e3map.contents.MessageContent;
import pl.jsystems.advancedjava.streams.exercises.e3map.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
