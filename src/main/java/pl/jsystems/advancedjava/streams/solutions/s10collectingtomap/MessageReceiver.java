package pl.jsystems.advancedjava.streams.solutions.s10collectingtomap;

import pl.jsystems.advancedjava.streams.solutions.s10collectingtomap.contents.MessageContent;
import pl.jsystems.advancedjava.streams.solutions.s10collectingtomap.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
