package pl.jsystems.advancedjava.threads.solutions.s1creatingthreads.receivers;

import pl.jsystems.advancedjava.threads.solutions.s1creatingthreads.MessageReceiver;
import pl.jsystems.advancedjava.threads.solutions.s1creatingthreads.contents.CargoUnloadedMessageContent;
import pl.jsystems.advancedjava.threads.solutions.s1creatingthreads.message.Message;
import pl.jsystems.advancedjava.threads.solutions.s1creatingthreads.message.MessageCreator;

import java.util.function.Consumer;
import java.util.stream.IntStream;

public class CargoUnloadedMessageReceiver implements MessageReceiver<CargoUnloadedMessageContent>
{
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
                        messageConsumer.accept(message);
                        waitABit();
                    });
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
            throw new RuntimeException("Thread has been interrupted while waiting for new message.", e);
        }
    }
}