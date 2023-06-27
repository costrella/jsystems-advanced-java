package pl.jsystems.advancedjava.reflection.solutions.s2breakingstuff;

import pl.jsystems.advancedjava.reflection.solutions.s2breakingstuff.contents.MessageContent;
import pl.jsystems.advancedjava.reflection.solutions.s2breakingstuff.message.Message;

import java.util.concurrent.Executor;
import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Executor executor, Consumer<Message<T>> messageConsumer);
}
