package pl.jsystems.advancedjava.threads.solutions.s9synchronizedstate.receivers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.threads.solutions.s9synchronizedstate.MessageReceiver;
import pl.jsystems.advancedjava.threads.solutions.s9synchronizedstate.contents.CargoUnloadedMessageContent;
import pl.jsystems.advancedjava.threads.solutions.s9synchronizedstate.message.Message;
import pl.jsystems.advancedjava.threads.solutions.s9synchronizedstate.message.MessageCreator;

import java.util.function.Consumer;
import java.util.stream.IntStream;

public class CargoUnloadedMessageReceiver implements MessageReceiver<CargoUnloadedMessageContent>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CargoUnloadedMessageReceiver.class);
    private final MessageCreator messageCreator = new MessageCreator();

    @Override
    public void startReceivingUsing(Consumer<Message<CargoUnloadedMessageContent>> messageConsumer)
    {
        new ReceiverThread(messageConsumer).start();
    }

    private class ReceiverThread extends Thread
    {

        private final Consumer<Message<CargoUnloadedMessageContent>> messageConsumer;

        private ReceiverThread(Consumer<Message<CargoUnloadedMessageContent>> messageConsumer)
        {
            this.messageConsumer = messageConsumer;
        }

        @Override
        public void run()
        {
            IntStream.range(0, 11)
                    .mapToObj(ignored -> messageCreator.createMessageUsing(new CargoUnloadedMessageContent()))
                    .forEach(message ->
                    {
                        LOGGER.info("Sending new message: {}", message.id());
                        messageConsumer.accept(message);
                        waitABit();
                    });
            LOGGER.info("Cargo Unloaded message receiver job is done.");
        }
    }

    private static void waitABit()
    {
        try
        {
            Thread.sleep(100);
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            LOGGER.error("Thread has been interrupted while waiting for new message.", e);
            throw new RuntimeException("Thread has been interrupted while waiting for new message.", e);
        }
    }
}