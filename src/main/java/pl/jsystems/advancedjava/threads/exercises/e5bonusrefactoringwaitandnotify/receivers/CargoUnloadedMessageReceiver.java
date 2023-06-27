package pl.jsystems.advancedjava.threads.exercises.e5bonusrefactoringwaitandnotify.receivers;

import pl.jsystems.advancedjava.threads.exercises.e5bonusrefactoringwaitandnotify.MessageReceiver;
import pl.jsystems.advancedjava.threads.exercises.e5bonusrefactoringwaitandnotify.message.Message;
import pl.jsystems.advancedjava.threads.exercises.e5bonusrefactoringwaitandnotify.message.MessageCreator;
import pl.jsystems.advancedjava.threads.exercises.e5bonusrefactoringwaitandnotify.contents.CargoUnloadedMessageContent;

import java.util.function.Consumer;
import java.util.stream.IntStream;

public class CargoUnloadedMessageReceiver implements MessageReceiver<CargoUnloadedMessageContent>
{
    private final MessageCreator messageCreator = new MessageCreator();

    @Override
    public Thread startReceivingUsing(Consumer<Message<CargoUnloadedMessageContent>> messageConsumer)
    {
        Thread thread = new ReceiverThread(messageConsumer);
        thread.start();
        return thread;
    }

    private static void joinThread(final Thread thread)
    {
        try
        {
            thread.join();
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread has been interrupted while waiting to finish receiving new message.", e);
        }
    }

    private class ReceiverThread extends Thread {

        private final Consumer<Message<CargoUnloadedMessageContent>> messageConsumer;

        private ReceiverThread(Consumer<Message<CargoUnloadedMessageContent>> messageConsumer)
        {
            this.messageConsumer = messageConsumer;
        }

        @Override
        public void run() {
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
