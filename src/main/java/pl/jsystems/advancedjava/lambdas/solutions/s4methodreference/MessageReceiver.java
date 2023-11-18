package pl.jsystems.advancedjava.lambdas.solutions.s4methodreference;

import pl.jsystems.advancedjava.lambdas.solutions.s4methodreference.contents.MessageContent;
import pl.jsystems.advancedjava.lambdas.solutions.s4methodreference.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}