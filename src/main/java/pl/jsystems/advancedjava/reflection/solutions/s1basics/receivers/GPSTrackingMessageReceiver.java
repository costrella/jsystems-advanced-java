package pl.jsystems.advancedjava.reflection.solutions.s1basics.receivers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.reflection.solutions.s1basics.contents.GPSTrackingMessageContent;
import pl.jsystems.advancedjava.reflection.solutions.s1basics.MessageReceiver;
import pl.jsystems.advancedjava.reflection.solutions.s1basics.message.Message;
import pl.jsystems.advancedjava.reflection.solutions.s1basics.message.MessageCreator;

import java.util.concurrent.Executor;
import java.util.function.Consumer;

public class GPSTrackingMessageReceiver implements MessageReceiver<GPSTrackingMessageContent>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GPSTrackingMessageReceiver.class);

    private final MessageCreator messageCreator = new MessageCreator();

    @Override
    public void startReceivingUsing(Executor executor, Consumer<Message<GPSTrackingMessageContent>> messageConsumer)
    {
        executor.execute(() ->
        {
            while (true)
            {
                var message = messageCreator.createMessageUsing(new GPSTrackingMessageContent());
                LOGGER.info("Sending new delayed message: {}", message.id());
                messageConsumer.accept(message);
                waitABit();
            }
        });
        LOGGER.info("GPS message receiver job is done.");
    }

    private static void waitABit()
    {
        try
        {
            Thread.sleep(400);
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            LOGGER.error("Thread has been interrupted while waiting for new message.", e);
            throw new RuntimeException("Thread has been interrupted while waiting for new message.", e);
        }
    }
}
