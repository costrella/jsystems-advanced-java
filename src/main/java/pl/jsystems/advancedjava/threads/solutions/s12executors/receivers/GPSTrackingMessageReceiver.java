package pl.jsystems.advancedjava.threads.solutions.s12executors.receivers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.threads.solutions.s12executors.MessageReceiver;
import pl.jsystems.advancedjava.threads.solutions.s12executors.contents.GPSTrackingMessageContent;
import pl.jsystems.advancedjava.threads.solutions.s12executors.message.Message;
import pl.jsystems.advancedjava.threads.solutions.s12executors.message.MessageCreator;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class GPSTrackingMessageReceiver implements MessageReceiver<GPSTrackingMessageContent>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GPSTrackingMessageReceiver.class);

    private final MessageCreator messageCreator = new MessageCreator();

    @Override
    public void startReceivingUsing(Consumer<Message<GPSTrackingMessageContent>> messageConsumer)
    {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleWithFixedDelay(() ->
        {
            var message = messageCreator.createMessageUsing(new GPSTrackingMessageContent());
            LOGGER.info("Sending new delayed message: {}", message.id());
            messageConsumer.accept(message);
            waitABit();
        }, 1L, 500L, TimeUnit.MILLISECONDS);
        LOGGER.info("GPS message receiver job is done.");
    }

    private static void waitABit()
    {
        try
        {
            Thread.sleep(500);
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            LOGGER.error("Thread has been interrupted while waiting for new message.", e);
            throw new RuntimeException("Thread has been interrupted while waiting for new message.", e);
        }
    }
}
