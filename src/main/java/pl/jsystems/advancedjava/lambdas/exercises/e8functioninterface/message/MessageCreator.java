package pl.jsystems.advancedjava.lambdas.exercises.e8functioninterface.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.lambdas.exercises.e8functioninterface.contents.MessageContent;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.random.RandomGenerator;

public class MessageCreator
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageCreator.class);

    public <CONTENT extends MessageContent> Message<CONTENT> createMessageUsing(CONTENT content)
    {
        int delayInMinutes = RandomGenerator.getDefault().nextInt(120);
        Message<CONTENT> message = new Message<>(
                UUID.randomUUID(),
                content,
                Instant.now().minus(delayInMinutes, ChronoUnit.MINUTES));

        LOGGER.info("Created message with UUID: {}", message);
        return message;
    }
}