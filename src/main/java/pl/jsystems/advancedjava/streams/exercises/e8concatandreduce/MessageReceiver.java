package pl.jsystems.advancedjava.streams.exercises.e8concatandreduce;

import pl.jsystems.advancedjava.streams.exercises.e8concatandreduce.contents.MessageContent;
import pl.jsystems.advancedjava.streams.exercises.e8concatandreduce.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
