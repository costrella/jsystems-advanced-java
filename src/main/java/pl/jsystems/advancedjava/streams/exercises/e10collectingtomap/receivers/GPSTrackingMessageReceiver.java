package pl.jsystems.advancedjava.streams.exercises.e10collectingtomap.receivers;

import pl.jsystems.advancedjava.streams.exercises.e10collectingtomap.MessageReceiver;
import pl.jsystems.advancedjava.streams.exercises.e10collectingtomap.contents.GPSTrackingMessageContent;
import pl.jsystems.advancedjava.streams.exercises.e10collectingtomap.message.Message;
import pl.jsystems.advancedjava.streams.exercises.e10collectingtomap.message.MessageCreator;

import java.util.function.Consumer;
import java.util.stream.IntStream;

public class GPSTrackingMessageReceiver implements MessageReceiver<GPSTrackingMessageContent>
{
    private final MessageCreator messageCreator = new MessageCreator();

    @Override
    public void startReceivingUsing(Consumer<Message<GPSTrackingMessageContent>> messageConsumer)
    {
        IntStream.range(0, 11)
                .mapToObj(ignored -> messageCreator.createMessageUsing(new GPSTrackingMessageContent()))
                .forEach(message ->
                {
                    messageConsumer.accept(message);
                    waitABit();
                });
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
