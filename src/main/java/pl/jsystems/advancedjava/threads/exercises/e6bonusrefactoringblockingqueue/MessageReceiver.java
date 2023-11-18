package pl.jsystems.advancedjava.threads.exercises.e6bonusrefactoringblockingqueue;

import pl.jsystems.advancedjava.threads.exercises.e6bonusrefactoringblockingqueue.contents.MessageContent;
import pl.jsystems.advancedjava.threads.exercises.e6bonusrefactoringblockingqueue.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}