package pl.jsystems.advancedjava.streams.exercises.e6optional.receivers;

import pl.jsystems.advancedjava.streams.exercises.e6optional.MessageReceiver;
import pl.jsystems.advancedjava.streams.exercises.e6optional.message.Message;
import pl.jsystems.advancedjava.streams.exercises.e6optional.message.MessageCreator;
import pl.jsystems.advancedjava.streams.exercises.e6optional.contents.GPSTrackingMessageContent;

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
