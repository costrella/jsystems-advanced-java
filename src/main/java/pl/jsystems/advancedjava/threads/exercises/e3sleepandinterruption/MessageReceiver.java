package pl.jsystems.advancedjava.threads.exercises.e3sleepandinterruption;

import pl.jsystems.advancedjava.threads.exercises.e3sleepandinterruption.contents.MessageContent;
import pl.jsystems.advancedjava.threads.exercises.e3sleepandinterruption.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    Thread startReceivingUsing(Consumer<Message<T>> messageConsumer);
}