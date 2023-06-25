package pl.jsystems.advancedjava.threads.solutions.s12executors.receivers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.threads.solutions.s12executors.MessageReceiver;
import pl.jsystems.advancedjava.threads.solutions.s12executors.contents.CargoUnloadedMessageContent;
import pl.jsystems.advancedjava.threads.solutions.s12executors.message.Message;
import pl.jsystems.advancedjava.threads.solutions.s12executors.message.MessageCreator;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class CargoUnloadedMessageReceiver implements MessageReceiver<CargoUnloadedMessageContent>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CargoUnloadedMessageReceiver.class);
    private final MessageCreator messageCreator = new MessageCreator();

    @Override
    public void startReceivingUsing(Consumer<Message<CargoUnloadedMessageContent>> messageConsumer)
    {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(new ReceiverRunnable(messageConsumer),
                2, 1, TimeUnit.SECONDS);
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
            var message = messageCreator.createMessageUsing(new CargoUnloadedMessageContent());
            LOGGER.info("Sending new message: {}", message.id());
            messageConsumer.accept(message);
            LOGGER.info("Cargo Unloaded message receiver job is done.");
        }
    }
}
