package pl.jsystems.advancedjava.threads.solutions.s5bonusrefactoringwaitandnotify;

import pl.jsystems.advancedjava.threads.solutions.s5bonusrefactoringwaitandnotify.message.Message;
import pl.jsystems.advancedjava.threads.solutions.s5bonusrefactoringwaitandnotify.contents.MessageContent;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
