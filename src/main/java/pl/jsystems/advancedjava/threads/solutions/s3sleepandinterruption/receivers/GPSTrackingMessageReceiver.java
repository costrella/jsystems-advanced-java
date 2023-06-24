package pl.jsystems.advancedjava.threads.solutions.s3sleepandinterruption.receivers;

import pl.jsystems.advancedjava.threads.solutions.s3sleepandinterruption.MessageReceiver;
import pl.jsystems.advancedjava.threads.solutions.s3sleepandinterruption.contents.GPSTrackingMessageContent;
import pl.jsystems.advancedjava.threads.solutions.s3sleepandinterruption.message.Message;
import pl.jsystems.advancedjava.threads.solutions.s3sleepandinterruption.message.MessageCreator;

import java.util.function.Consumer;
import java.util.stream.IntStream;

public class GPSTrackingMessageReceiver implements MessageReceiver<GPSTrackingMessageContent>
{
    private final MessageCreator messageCreator = new MessageCreator();

    @Override
    public Thread startReceivingUsing(Consumer<Message<GPSTrackingMessageContent>> messageConsumer)
    {
        Runnable runnable = () -> IntStream.range(0, 11)
                .mapToObj(ignored -> messageCreator.createMessageUsing(new GPSTrackingMessageContent()))
                .forEach(message ->
                {
                    messageConsumer.accept(message);
                    waitABit();
                });

        Thread receivingThread = new Thread(runnable);
        receivingThread.start();
        return receivingThread;
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
