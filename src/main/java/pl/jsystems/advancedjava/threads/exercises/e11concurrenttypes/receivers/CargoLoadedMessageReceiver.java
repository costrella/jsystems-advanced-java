package pl.jsystems.advancedjava.threads.exercises.e11concurrenttypes.receivers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.threads.exercises.e11concurrenttypes.MessageReceiver;
import pl.jsystems.advancedjava.threads.exercises.e11concurrenttypes.contents.CargoLoadedMessageContent;
import pl.jsystems.advancedjava.threads.exercises.e11concurrenttypes.message.Message;
import pl.jsystems.advancedjava.threads.exercises.e11concurrenttypes.message.MessageCreator;

import java.util.function.Consumer;
import java.util.stream.IntStream;

public class CargoLoadedMessageReceiver implements MessageReceiver<CargoLoadedMessageContent>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CargoLoadedMessageReceiver.class);
    private final MessageCreator messageCreator = new MessageCreator();

    @Override
    public void startReceivingUsing(Consumer<Message<CargoLoadedMessageContent>> messageConsumer)
    {
        new Thread(() ->
        {
            IntStream.range(0, 100)
                    .mapToObj(ignored -> messageCreator.createMessageUsing(new CargoLoadedMessageContent()))
                    .forEach(message ->
                    {
                        LOGGER.info("Sending new message: {}", message.id());
                        messageConsumer.accept(message);
                     //   waitABit();
                    });
            LOGGER.info("Cargo Loaded message receiver job is done.");
        }).start();
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
