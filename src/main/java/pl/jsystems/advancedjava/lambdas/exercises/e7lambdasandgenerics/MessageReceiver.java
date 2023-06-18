package pl.jsystems.advancedjava.lambdas.exercises.e7lambdasandgenerics;

import pl.jsystems.advancedjava.lambdas.exercises.e7lambdasandgenerics.contents.MessageContent;
import pl.jsystems.advancedjava.lambdas.exercises.e7lambdasandgenerics.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
