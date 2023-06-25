package pl.jsystems.advancedjava.threads.exercises.e7refactoring;

import pl.jsystems.advancedjava.threads.exercises.e7refactoring.contents.MessageContent;
import pl.jsystems.advancedjava.threads.exercises.e7refactoring.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
