package pl.jsystems.advancedjava.lambdas.solutions.s3consumerlambda;

import pl.jsystems.advancedjava.lambdas.solutions.s3consumerlambda.contents.MessageContent;
import pl.jsystems.advancedjava.lambdas.solutions.s3consumerlambda.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
