package pl.jsystems.advancedjava.generics.solutions.s15revision;

import pl.jsystems.advancedjava.generics.solutions.s15revision.contents.MessageContent;
import pl.jsystems.advancedjava.generics.solutions.s15revision.message.Message;

public interface MessageReceiver<T extends MessageContent>
{
    Message<T> getLatestReceivedMessage();
}
