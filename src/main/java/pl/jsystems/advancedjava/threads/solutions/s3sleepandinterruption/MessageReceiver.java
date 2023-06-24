package pl.jsystems.advancedjava.threads.solutions.s3sleepandinterruption;

import pl.jsystems.advancedjava.threads.solutions.s3sleepandinterruption.message.Message;
import pl.jsystems.advancedjava.threads.solutions.s3sleepandinterruption.contents.MessageContent;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    Thread startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
