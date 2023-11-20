package pl.jsystems.advancedjava.lambdas.exercises.e1consumerinterface;

import pl.jsystems.advancedjava.lambdas.exercises.e1consumerinterface.contents.MessageContent;
import pl.jsystems.advancedjava.lambdas.exercises.e1consumerinterface.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    Message<T> startReceivingUsing(Consumer<Message<T>> messageConsumer);
}