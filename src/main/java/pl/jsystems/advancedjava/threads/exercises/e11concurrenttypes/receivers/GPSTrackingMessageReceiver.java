package pl.jsystems.advancedjava.threads.exercises.e11concurrenttypes.receivers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.threads.exercises.e11concurrenttypes.MessageReceiver;
import pl.jsystems.advancedjava.threads.exercises.e11concurrenttypes.contents.GPSTrackingMessageContent;
import pl.jsystems.advancedjava.threads.exercises.e11concurrenttypes.message.Message;
import pl.jsystems.advancedjava.threads.exercises.e11concurrenttypes.message.MessageCreator;

import java.util.function.Consumer;
import java.util.stream.IntStream;

public class GPSTrackingMessageReceiver implements MessageReceiver<GPSTrackingMessageContent>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GPSTrackingMessageReceiver.class);

    private final MessageCreator messageCreator = new MessageCreator();

    @Override
    public void startReceivingUsing(Consumer<Message<GPSTrackingMessageContent>> messageConsumer)
    {
        Runnable runnable = () ->
        {
            IntStream.range(0, 100)
                    .mapToObj(ignored -> messageCreator.createMessageUsing(new GPSTrackingMessageContent()))
                    .forEach(message ->
                    {
                        LOGGER.info("Sending new message: {}", message.id());
                        messageConsumer.accept(message);
                       // waitABit();
                    });
            LOGGER.info("GPS message receiver job is done.");
        };

        new Thread(runnable).start();
    }

    private static void waitABit()
    {
        try
        {
            Thread.sleep(10);
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            LOGGER.error("Thread has been interrupted while waiting for new message.", e);
            throw new RuntimeException("Thread has been interrupted while waiting for new message.", e);
        }
    }
}
