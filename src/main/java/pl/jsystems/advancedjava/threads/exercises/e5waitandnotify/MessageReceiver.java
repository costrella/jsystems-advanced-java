package pl.jsystems.advancedjava.threads.exercises.e5waitandnotify;

import pl.jsystems.advancedjava.threads.exercises.e5waitandnotify.message.Message;
import pl.jsystems.advancedjava.threads.exercises.e5waitandnotify.contents.MessageContent;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    Thread startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
