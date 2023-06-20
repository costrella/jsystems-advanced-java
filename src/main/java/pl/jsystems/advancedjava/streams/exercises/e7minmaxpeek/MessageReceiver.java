package pl.jsystems.advancedjava.streams.exercises.e7minmaxpeek;

import pl.jsystems.advancedjava.streams.exercises.e7minmaxpeek.contents.MessageContent;
import pl.jsystems.advancedjava.streams.exercises.e7minmaxpeek.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
