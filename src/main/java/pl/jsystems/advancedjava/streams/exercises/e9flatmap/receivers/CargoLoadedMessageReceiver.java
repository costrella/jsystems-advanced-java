package pl.jsystems.advancedjava.streams.exercises.e9flatmap.receivers;

import pl.jsystems.advancedjava.streams.exercises.e9flatmap.MessageReceiver;
import pl.jsystems.advancedjava.streams.exercises.e9flatmap.contents.CargoLoadedMessageContent;
import pl.jsystems.advancedjava.streams.exercises.e9flatmap.message.Message;
import pl.jsystems.advancedjava.streams.exercises.e9flatmap.message.MessageCreator;

import java.util.function.Consumer;

public class CargoLoadedMessageReceiver implements MessageReceiver<CargoLoadedMessageContent>
{
    private final MessageCreator messageCreator = new MessageCreator();

    @Override
    public void startReceivingUsing(Consumer<Message<CargoLoadedMessageContent>> messageConsumer)
    {
        for (int i = 0; i < 10; i++) // todo stuff
        {
            waitABit();
            Message<CargoLoadedMessageContent> newMessage = messageCreator.createMessageUsing(new CargoLoadedMessageContent());
            messageConsumer.accept(newMessage);
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
