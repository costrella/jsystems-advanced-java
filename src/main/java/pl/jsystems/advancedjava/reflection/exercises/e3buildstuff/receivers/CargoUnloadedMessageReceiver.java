package pl.jsystems.advancedjava.reflection.exercises.e3buildstuff.receivers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.reflection.exercises.e3buildstuff.MessageReceiver;
import pl.jsystems.advancedjava.reflection.exercises.e3buildstuff.contents.CargoUnloadedMessageContent;
import pl.jsystems.advancedjava.reflection.exercises.e3buildstuff.message.Message;
import pl.jsystems.advancedjava.reflection.exercises.e3buildstuff.message.MessageCreator;

import java.util.concurrent.Executor;
import java.util.function.Consumer;

public class CargoUnloadedMessageReceiver implements MessageReceiver<CargoUnloadedMessageContent>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CargoUnloadedMessageReceiver.class);
    private final MessageCreator messageCreator = new MessageCreator();

    @Override
    public void startReceivingUsing(Executor executor, Consumer<Message<CargoUnloadedMessageContent>> messageConsumer)
    {
        executor.execute(new ReceiverRunnable(messageConsumer));
    }

    private class ReceiverRunnable implements Runnable
    {

        private final Consumer<Message<CargoUnloadedMessageContent>> messageConsumer;

        private ReceiverRunnable(Consumer<Message<CargoUnloadedMessageContent>> messageConsumer)
        {
            this.messageConsumer = messageConsumer;
        }

        @Override
        public void run()
        {
            while (true)
            {
                var message = messageCreator.createMessageUsing(new CargoUnloadedMessageContent());
                LOGGER.info("Sending new message: {}", message.id());
                messageConsumer.accept(message);
                LOGGER.info("Cargo Unloaded message receiver job is done.");
                waitABit();
            }

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
}