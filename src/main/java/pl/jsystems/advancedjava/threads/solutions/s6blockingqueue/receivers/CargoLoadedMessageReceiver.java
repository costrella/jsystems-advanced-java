package pl.jsystems.advancedjava.threads.solutions.s6blockingqueue.receivers;

import pl.jsystems.advancedjava.threads.solutions.s6blockingqueue.MessageReceiver;
import pl.jsystems.advancedjava.threads.solutions.s6blockingqueue.contents.CargoLoadedMessageContent;
import pl.jsystems.advancedjava.threads.solutions.s6blockingqueue.message.Message;
import pl.jsystems.advancedjava.threads.solutions.s6blockingqueue.message.MessageCreator;

import java.util.function.Consumer;
import java.util.stream.IntStream;

public class CargoLoadedMessageReceiver implements MessageReceiver<CargoLoadedMessageContent>
{
    private final MessageCreator messageCreator = new MessageCreator();

    @Override
    public Thread startReceivingUsing(Consumer<Message<CargoLoadedMessageContent>> messageConsumer)
    {
        Thread thread = new Thread(() ->
                IntStream.range(0, 11)
                        .mapToObj(ignored -> messageCreator.createMessageUsing(new CargoLoadedMessageContent()))
                        .forEach(message ->
                        {
                            messageConsumer.accept(message);
                            waitABit();
                        }));
        thread.start();
        return thread;
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
