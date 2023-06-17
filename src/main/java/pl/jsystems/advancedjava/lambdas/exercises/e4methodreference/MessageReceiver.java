package pl.jsystems.advancedjava.lambdas.exercises.e4methodreference;

import pl.jsystems.advancedjava.lambdas.exercises.e4methodreference.message.Message;
import pl.jsystems.advancedjava.lambdas.exercises.e4methodreference.contents.MessageContent;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
