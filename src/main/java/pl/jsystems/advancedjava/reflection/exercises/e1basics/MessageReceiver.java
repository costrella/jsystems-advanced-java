package pl.jsystems.advancedjava.reflection.exercises.e1basics;

import pl.jsystems.advancedjava.reflection.exercises.e1basics.contents.MessageContent;
import pl.jsystems.advancedjava.reflection.exercises.e1basics.message.Message;

import java.util.concurrent.Executor;
import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Executor executor, Consumer<Message<T>> messageConsumer);
}
