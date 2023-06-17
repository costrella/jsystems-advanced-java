package pl.jsystems.advancedjava.lambdas.exercises.e2anonymousclasses;

import pl.jsystems.advancedjava.lambdas.exercises.e2anonymousclasses.contents.MessageContent;
import pl.jsystems.advancedjava.lambdas.exercises.e2anonymousclasses.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
