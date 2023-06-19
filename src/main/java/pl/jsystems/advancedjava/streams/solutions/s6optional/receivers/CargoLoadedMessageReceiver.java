package pl.jsystems.advancedjava.streams.solutions.s6optional.receivers;

import pl.jsystems.advancedjava.streams.solutions.s6optional.MessageReceiver;
import pl.jsystems.advancedjava.streams.solutions.s6optional.message.Message;
import pl.jsystems.advancedjava.streams.solutions.s6optional.message.MessageCreator;
import pl.jsystems.advancedjava.streams.solutions.s6optional.contents.CargoLoadedMessageContent;

import java.util.function.Consumer;

public class CargoLoadedMessageReceiver implements MessageReceiver<CargoLoadedMessageContent>
{
    private final MessageCreator messageCreator = new MessageCreator();

    @Override
    public void startReceivingUsing(Consumer<Message<CargoLoadedMessageContent>> messageConsumer)
    {
        for (int i = 0; i < 10; i++)
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
