package pl.jsystems.advancedjava.lambdas.solutions.s1consumerinterface;

import pl.jsystems.advancedjava.lambdas.solutions.s1consumerinterface.contents.MessageContent;
import pl.jsystems.advancedjava.lambdas.solutions.s1consumerinterface.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
