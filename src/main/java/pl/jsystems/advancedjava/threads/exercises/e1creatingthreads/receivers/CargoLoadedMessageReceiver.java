package pl.jsystems.advancedjava.threads.exercises.e1creatingthreads.receivers;

import pl.jsystems.advancedjava.threads.exercises.e1creatingthreads.MessageReceiver;
import pl.jsystems.advancedjava.threads.exercises.e1creatingthreads.contents.CargoLoadedMessageContent;
import pl.jsystems.advancedjava.threads.exercises.e1creatingthreads.message.Message;
import pl.jsystems.advancedjava.threads.exercises.e1creatingthreads.message.MessageCreator;

import java.util.function.Consumer;
import java.util.stream.IntStream;

public class CargoLoadedMessageReceiver implements MessageReceiver<CargoLoadedMessageContent>
{
    private final MessageCreator messageCreator = new MessageCreator();

    @Override
    public void startReceivingUsing(Consumer<Message<CargoLoadedMessageContent>> messageConsumer)
    {
        IntStream.range(0, 11)
                .mapToObj(ignored -> messageCreator.createMessageUsing(new CargoLoadedMessageContent()))
                .forEach(message ->
                {
                    messageConsumer.accept(message);
                    waitABit();
                });
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
