package pl.jsystems.advancedjava.reflection.solutions.s3buildstuff;

import pl.jsystems.advancedjava.reflection.solutions.s3buildstuff.contents.MessageContent;
import pl.jsystems.advancedjava.reflection.solutions.s3buildstuff.message.Message;

import java.util.concurrent.Executor;
import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Executor executor, Consumer<Message<T>> messageConsumer);
}
