package pl.jsystems.advancedjava.reflection.solutions.s4readfromfile;

import pl.jsystems.advancedjava.reflection.solutions.s4readfromfile.contents.MessageContent;
import pl.jsystems.advancedjava.reflection.solutions.s4readfromfile.message.Message;

import java.util.concurrent.Executor;
import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Executor executor, Consumer<Message<T>> messageConsumer);
}
