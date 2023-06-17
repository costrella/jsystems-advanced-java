package pl.jsystems.advancedjava.lambdas.exercises.e1consumerinterface;

import pl.jsystems.advancedjava.lambdas.exercises.e1consumerinterface.contents.GPSTrackingMessageContent;
import pl.jsystems.advancedjava.lambdas.exercises.e1consumerinterface.message.Message;
import pl.jsystems.advancedjava.lambdas.exercises.e1consumerinterface.message.MessageCreator;

class GPSTrackingMessageReceiver implements MessageReceiver<GPSTrackingMessageContent>
{
    private final MessageCreator messageCreator = new MessageCreator();

    @Override
    public Message<GPSTrackingMessageContent> getLatestReceivedMessage()
    {
        Message<GPSTrackingMessageContent> newMessage = null;
        for (int i = 0; i < 10; i++)
        {
            waitABit();
            newMessage = messageCreator.createMessageUsing(new GPSTrackingMessageContent());

            // TODO: handle it somehow...

        }
        return newMessage;
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
