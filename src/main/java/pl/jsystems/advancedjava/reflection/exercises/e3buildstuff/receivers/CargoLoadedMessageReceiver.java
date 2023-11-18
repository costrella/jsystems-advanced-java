package pl.jsystems.advancedjava.reflection.exercises.e3buildstuff.receivers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.reflection.exercises.e3buildstuff.MessageReceiver;
import pl.jsystems.advancedjava.reflection.exercises.e3buildstuff.contents.CargoLoadedMessageContent;
import pl.jsystems.advancedjava.reflection.exercises.e3buildstuff.message.Message;
import pl.jsystems.advancedjava.reflection.exercises.e3buildstuff.message.MessageCreator;

import java.util.concurrent.Executor;
import java.util.function.Consumer;

public class CargoLoadedMessageReceiver implements MessageReceiver<CargoLoadedMessageContent>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CargoLoadedMessageReceiver.class);
    private final MessageCreator messageCreator = new MessageCreator();

    @Override
    public void startReceivingUsing(Executor executor, Consumer<Message<CargoLoadedMessageContent>> messageConsumer)
    {
        executor.execute(() ->
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
            Thread.sleep(200);
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            LOGGER.error("Thread has been interrupted while waiting for new message.", e);
            throw new RuntimeException("Thread has been interrupted while waiting for new message.", e);
        }
    }
}