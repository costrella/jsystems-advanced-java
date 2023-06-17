package pl.jsystems.advancedjava.lambdas.solutions.s2anonymousclasses;

import pl.jsystems.advancedjava.lambdas.solutions.s2anonymousclasses.contents.MessageContent;
import pl.jsystems.advancedjava.lambdas.solutions.s2anonymousclasses.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
