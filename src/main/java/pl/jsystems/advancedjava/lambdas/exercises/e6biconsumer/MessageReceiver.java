package pl.jsystems.advancedjava.lambdas.exercises.e6biconsumer;

import pl.jsystems.advancedjava.lambdas.exercises.e6biconsumer.contents.MessageContent;
import pl.jsystems.advancedjava.lambdas.exercises.e6biconsumer.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
