package pl.jsystems.advancedjava.threads.solutions.s13future;

import pl.jsystems.advancedjava.threads.solutions.s13future.contents.MessageContent;
import pl.jsystems.advancedjava.threads.solutions.s13future.message.Message;

import java.util.concurrent.Executor;
import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Executor executor, Consumer<Message<T>> messageConsumer);
}
