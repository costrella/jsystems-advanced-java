package pl.jsystems.advancedjava.streams.solutions.s7minmaxpeek;

import pl.jsystems.advancedjava.streams.solutions.s7minmaxpeek.contents.MessageContent;
import pl.jsystems.advancedjava.streams.solutions.s7minmaxpeek.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
