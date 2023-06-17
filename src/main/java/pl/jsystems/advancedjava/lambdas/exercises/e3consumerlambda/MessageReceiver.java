package pl.jsystems.advancedjava.lambdas.exercises.e3consumerlambda;

import pl.jsystems.advancedjava.lambdas.exercises.e3consumerlambda.contents.MessageContent;
import pl.jsystems.advancedjava.lambdas.exercises.e3consumerlambda.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
