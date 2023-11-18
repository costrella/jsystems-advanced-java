package pl.jsystems.advancedjava.threads.exercises.e2joiningthreads;

import pl.jsystems.advancedjava.threads.exercises.e2joiningthreads.contents.MessageContent;
import pl.jsystems.advancedjava.threads.exercises.e2joiningthreads.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}