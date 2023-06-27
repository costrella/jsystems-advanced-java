package pl.jsystems.advancedjava.threads.solutions.s7bonusrefactoring;

import pl.jsystems.advancedjava.threads.solutions.s7bonusrefactoring.contents.MessageContent;
import pl.jsystems.advancedjava.threads.solutions.s7bonusrefactoring.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
