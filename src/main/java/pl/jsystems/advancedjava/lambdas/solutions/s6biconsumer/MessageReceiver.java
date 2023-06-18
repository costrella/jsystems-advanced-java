package pl.jsystems.advancedjava.lambdas.solutions.s6biconsumer;

import pl.jsystems.advancedjava.lambdas.solutions.s6biconsumer.contents.MessageContent;
import pl.jsystems.advancedjava.lambdas.solutions.s6biconsumer.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
