package pl.jsystems.advancedjava.generics.solutions.s2genericmessageid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class GenericsExercise2GenericMessageId
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericsExercise2GenericMessageId.class);

    public static void main(String[] args)
    {
        new GenericsExercise2GenericMessageId().run();
    }

    private void run()
    {
        LOGGER.info("Creating new message with id...");
        Message<Long, Integer> message = new Message<>(42L, 42);
        LOGGER.info("Message: {}", message);
        LOGGER.info("Message content: {}", message.content());

        LOGGER.info("Creating new message with id...");
        Message<String, String> otherMessage = new Message<>("Something", "else");
        LOGGER.info("Message: {}", otherMessage);
        LOGGER.info("Message content: {}", otherMessage.content());

        LOGGER.info("Creating new message with id...");
        Message<Double, Integer> yetAnotherMessage = new Message<>(1d, 42);
        LOGGER.info("Message: {}", yetAnotherMessage);
        LOGGER.info("Message content: {}", yetAnotherMessage.content());
    }
}
