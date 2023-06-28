package pl.jsystems.advancedjava.reflection.exercises.e4readfromfile.receivers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.reflection.exercises.e4readfromfile.ForDependencyInjection;
import pl.jsystems.advancedjava.reflection.exercises.e4readfromfile.contents.CargoLoadedMessageContent;
import pl.jsystems.advancedjava.reflection.exercises.e4readfromfile.message.Message;
import pl.jsystems.advancedjava.reflection.exercises.e4readfromfile.message.MessageCreator;
import pl.jsystems.advancedjava.reflection.exercises.e4readfromfile.MessageReceiver;

import java.util.concurrent.Executor;
import java.util.function.Consumer;

@ForDependencyInjection
public class CargoLoadedMessageReceiver implements MessageReceiver<CargoLoadedMessageContent>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CargoLoadedMessageReceiver.class);

    private final MessageCreator messageCreator;

    public CargoLoadedMessageReceiver(MessageCreator messageCreator) {
        this.messageCreator = messageCreator;
    }

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
