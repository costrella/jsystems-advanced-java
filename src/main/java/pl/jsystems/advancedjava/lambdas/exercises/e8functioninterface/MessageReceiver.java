package pl.jsystems.advancedjava.lambdas.exercises.e8functioninterface;

import pl.jsystems.advancedjava.lambdas.exercises.e8functioninterface.contents.MessageContent;
import pl.jsystems.advancedjava.lambdas.exercises.e8functioninterface.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
