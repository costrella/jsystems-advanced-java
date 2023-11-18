package pl.jsystems.advancedjava.streams.solutions.s12partitioning.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.streams.solutions.s12partitioning.contents.MessageContent;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.random.RandomGenerator;

public class MessageCreator
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageCreator.class);

    // temp solution to test / work with distinct

    public <CONTENT extends MessageContent> Message<CONTENT> createMessageUsing(CONTENT content)
    {
        int delayInDays = RandomGenerator.getDefault().nextInt(3);

        Message<CONTENT> message = new Message<>(
                UUID.randomUUID(),
                content,
                Instant.now().minus(delayInDays, ChronoUnit.DAYS));

        LOGGER.info("Created message with UUID: {}", message);
        return message;
    }
}