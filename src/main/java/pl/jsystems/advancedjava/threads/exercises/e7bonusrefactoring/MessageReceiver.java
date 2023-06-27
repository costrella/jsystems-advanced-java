package pl.jsystems.advancedjava.threads.exercises.e7bonusrefactoring;

import pl.jsystems.advancedjava.threads.exercises.e7bonusrefactoring.contents.MessageContent;
import pl.jsystems.advancedjava.threads.exercises.e7bonusrefactoring.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
