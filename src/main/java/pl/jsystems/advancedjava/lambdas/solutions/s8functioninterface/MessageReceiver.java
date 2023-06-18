package pl.jsystems.advancedjava.lambdas.solutions.s8functioninterface;

import pl.jsystems.advancedjava.lambdas.solutions.s8functioninterface.contents.MessageContent;
import pl.jsystems.advancedjava.lambdas.solutions.s8functioninterface.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
