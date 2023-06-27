package pl.jsystems.advancedjava.threads.exercises.e5bonusrefactoringwaitandnotify;

import pl.jsystems.advancedjava.threads.exercises.e5bonusrefactoringwaitandnotify.message.Message;
import pl.jsystems.advancedjava.threads.exercises.e5bonusrefactoringwaitandnotify.contents.MessageContent;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    Thread startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
