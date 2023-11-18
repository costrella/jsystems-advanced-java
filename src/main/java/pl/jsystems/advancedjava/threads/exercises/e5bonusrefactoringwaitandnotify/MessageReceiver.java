package pl.jsystems.advancedjava.threads.exercises.e5bonusrefactoringwaitandnotify;

import pl.jsystems.advancedjava.threads.exercises.e5bonusrefactoringwaitandnotify.contents.MessageContent;
import pl.jsystems.advancedjava.threads.exercises.e5bonusrefactoringwaitandnotify.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    Thread startReceivingUsing(Consumer<Message<T>> messageConsumer);
}