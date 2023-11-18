package pl.jsystems.advancedjava.threads.solutions.s2joiningthreads;

import pl.jsystems.advancedjava.threads.solutions.s2joiningthreads.contents.MessageContent;
import pl.jsystems.advancedjava.threads.solutions.s2joiningthreads.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    Thread startReceivingUsing(Consumer<Message<T>> messageConsumer);
}