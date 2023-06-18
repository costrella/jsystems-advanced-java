package pl.jsystems.advancedjava.lambdas.exercises.e5predicateinterface;

import pl.jsystems.advancedjava.lambdas.exercises.e5predicateinterface.contents.MessageContent;
import pl.jsystems.advancedjava.lambdas.exercises.e5predicateinterface.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
