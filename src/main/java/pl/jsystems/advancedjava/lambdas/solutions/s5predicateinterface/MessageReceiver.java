package pl.jsystems.advancedjava.lambdas.solutions.s5predicateinterface;

import pl.jsystems.advancedjava.lambdas.solutions.s5predicateinterface.contents.MessageContent;
import pl.jsystems.advancedjava.lambdas.solutions.s5predicateinterface.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
