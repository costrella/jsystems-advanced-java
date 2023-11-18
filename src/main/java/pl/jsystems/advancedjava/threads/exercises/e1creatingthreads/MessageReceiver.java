package pl.jsystems.advancedjava.threads.exercises.e1creatingthreads;

import pl.jsystems.advancedjava.threads.exercises.e1creatingthreads.contents.MessageContent;
import pl.jsystems.advancedjava.threads.exercises.e1creatingthreads.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}