package pl.jsystems.advancedjava.streams.solutions.s5distinct.receivers;

import pl.jsystems.advancedjava.streams.solutions.s5distinct.MessageReceiver;
import pl.jsystems.advancedjava.streams.solutions.s5distinct.contents.GPSTrackingMessageContent;
import pl.jsystems.advancedjava.streams.solutions.s5distinct.message.Message;
import pl.jsystems.advancedjava.streams.solutions.s5distinct.message.MessageCreator;

import java.util.function.Consumer;

public class GPSTrackingMessageReceiver implements MessageReceiver<GPSTrackingMessageContent>
{
    private final MessageCreator messageCreator = new MessageCreator();

    @Override
    public void startReceivingUsing(Consumer<Message<GPSTrackingMessageContent>> messageConsumer)
    {
        for (int i = 0; i < 10; i++)
        {
            waitABit();
            Message<GPSTrackingMessageContent> newMessage = messageCreator.createMessageUsing(new GPSTrackingMessageContent());
            messageConsumer.accept(newMessage);
        }
    }

    private static void waitABit()
    {
        try
        {
            Thread.sleep(100);
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread has been interrupted while waiting for new message.", e);
        }
    }
}