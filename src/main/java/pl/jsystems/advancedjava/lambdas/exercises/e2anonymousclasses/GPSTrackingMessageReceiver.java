package pl.jsystems.advancedjava.lambdas.exercises.e2anonymousclasses;

import pl.jsystems.advancedjava.lambdas.exercises.e2anonymousclasses.contents.GPSTrackingMessageContent;
import pl.jsystems.advancedjava.lambdas.exercises.e2anonymousclasses.message.Message;
import pl.jsystems.advancedjava.lambdas.exercises.e2anonymousclasses.message.MessageCreator;

import java.util.function.Consumer;

class GPSTrackingMessageReceiver implements MessageReceiver<GPSTrackingMessageContent>
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