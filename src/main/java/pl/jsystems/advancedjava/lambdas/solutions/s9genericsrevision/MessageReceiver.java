package pl.jsystems.advancedjava.lambdas.solutions.s9genericsrevision;

import pl.jsystems.advancedjava.lambdas.solutions.s9genericsrevision.contents.MessageContent;
import pl.jsystems.advancedjava.lambdas.solutions.s9genericsrevision.message.Message;

import java.util.function.Consumer;

public interface MessageReceiver<T extends MessageContent>
{
    void startReceivingUsing(Consumer<Message<T>> messageConsumer);
}
