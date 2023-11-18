package pl.jsystems.advancedjava.streams.exercises.e11grouping.receivers;

import pl.jsystems.advancedjava.streams.exercises.e11grouping.MessageReceiver;
import pl.jsystems.advancedjava.streams.exercises.e11grouping.contents.CargoUnloadedMessageContent;
import pl.jsystems.advancedjava.streams.exercises.e11grouping.message.Message;
import pl.jsystems.advancedjava.streams.exercises.e11grouping.message.MessageCreator;

import java.util.function.Consumer;
import java.util.stream.IntStream;

public class CargoUnloadedMessageReceiver implements MessageReceiver<CargoUnloadedMessageContent>
{
    private final MessageCreator messageCreator = new MessageCreator();

    @Override
    public void startReceivingUsing(Consumer<Message<CargoUnloadedMessageContent>> messageConsumer)
    {
        IntStream.range(0, 11)
                .mapToObj(ignored -> messageCreator.createMessageUsing(new CargoUnloadedMessageContent()))
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