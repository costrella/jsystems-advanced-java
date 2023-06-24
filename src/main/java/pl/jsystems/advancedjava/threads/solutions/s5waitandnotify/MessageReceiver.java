package pl.jsystems.advancedjava.threads.solutions.s5waitandnotify;

import pl.jsystems.advancedjava.threads.solutions.s5waitandnotify.message.Message;
import pl.jsystems.advancedjava.threads.solutions.s5waitandnotify.contents.MessageContent;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
