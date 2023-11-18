package pl.jsystems.advancedjava.streams.exercises.e8concatandreduce.receivers;

import pl.jsystems.advancedjava.streams.exercises.e8concatandreduce.MessageReceiver;
import pl.jsystems.advancedjava.streams.exercises.e8concatandreduce.contents.CargoUnloadedMessageContent;
import pl.jsystems.advancedjava.streams.exercises.e8concatandreduce.message.Message;
import pl.jsystems.advancedjava.streams.exercises.e8concatandreduce.message.MessageCreator;

import java.util.function.Consumer;

public class CargoUnloadedMessageReceiver implements MessageReceiver<CargoUnloadedMessageContent>
{
    private final MessageCreator messageCreator = new MessageCreator();

    @Override
    public void startReceivingUsing(Consumer<Message<CargoUnloadedMessageContent>> messageConsumer)
    {
        for (int i = 0; i < 10; i++)
        {
            waitABit();
            Message<CargoUnloadedMessageContent> newMessage = messageCreator.createMessageUsing(new CargoUnloadedMessageContent());
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