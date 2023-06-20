package pl.jsystems.advancedjava.streams.solutions.s8concatandreduce;

import pl.jsystems.advancedjava.streams.solutions.s8concatandreduce.contents.MessageContent;
import pl.jsystems.advancedjava.streams.solutions.s8concatandreduce.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
