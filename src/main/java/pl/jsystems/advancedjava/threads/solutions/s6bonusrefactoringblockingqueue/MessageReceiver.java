package pl.jsystems.advancedjava.threads.solutions.s6bonusrefactoringblockingqueue;

import pl.jsystems.advancedjava.threads.solutions.s6bonusrefactoringblockingqueue.message.Message;
import pl.jsystems.advancedjava.threads.solutions.s6bonusrefactoringblockingqueue.contents.MessageContent;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
