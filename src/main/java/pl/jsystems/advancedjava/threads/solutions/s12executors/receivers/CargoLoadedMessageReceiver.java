package pl.jsystems.advancedjava.threads.solutions.s12executors.receivers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.threads.solutions.s12executors.MessageReceiver;
import pl.jsystems.advancedjava.threads.solutions.s12executors.contents.CargoLoadedMessageContent;
import pl.jsystems.advancedjava.threads.solutions.s12executors.message.Message;
import pl.jsystems.advancedjava.threads.solutions.s12executors.message.MessageCreator;

import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class CargoLoadedMessageReceiver implements MessageReceiver<CargoLoadedMessageContent>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CargoLoadedMessageReceiver.class);
    private final MessageCreator messageCreator = new MessageCreator();

    @Override
    public void startReceivingUsing(Consumer<Message<CargoLoadedMessageContent>> messageConsumer)
    {
        Executors.newSingleThreadExecutor().submit(() ->
        {
            while (true)
            {
                var message = messageCreator.createMessageUsing(new CargoLoadedMessageContent());
                LOGGER.info("Sending new message: {}", message.id());
                messageConsumer.accept(message);
                waitABit();
            }
            //LOGGER.info("Cargo Loaded message receiver job is done.");
        });
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
