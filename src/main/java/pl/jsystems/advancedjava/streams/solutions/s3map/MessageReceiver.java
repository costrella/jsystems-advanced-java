package pl.jsystems.advancedjava.streams.solutions.s3map;

import pl.jsystems.advancedjava.streams.solutions.s3map.contents.MessageContent;
import pl.jsystems.advancedjava.streams.solutions.s3map.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
