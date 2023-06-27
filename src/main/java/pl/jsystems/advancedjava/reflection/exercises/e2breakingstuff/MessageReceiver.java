package pl.jsystems.advancedjava.reflection.exercises.e2breakingstuff;

import pl.jsystems.advancedjava.reflection.exercises.e2breakingstuff.contents.MessageContent;
import pl.jsystems.advancedjava.reflection.exercises.e2breakingstuff.message.Message;

import java.util.concurrent.Executor;
import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Executor executor, Consumer<Message<T>> messageConsumer);
}
