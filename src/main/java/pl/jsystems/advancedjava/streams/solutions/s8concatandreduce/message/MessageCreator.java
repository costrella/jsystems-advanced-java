package pl.jsystems.advancedjava.streams.solutions.s8concatandreduce.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.streams.solutions.s8concatandreduce.contents.MessageContent;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.random.RandomGenerator;

public class MessageCreator
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageCreator.class);

    // temp solution to test / work with distinct
    private final List<UUID> ids = List.of(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());

    public <CONTENT extends MessageContent> Message<CONTENT> createMessageUsing(CONTENT content)
    {
        int delayInMinutes = RandomGenerator.getDefault().nextInt(120);

        Message<CONTENT> message = new Message<>(
                ids.get(RandomGenerator.getDefault().nextInt(0, 3)),
                content,
                Instant.now().minus(delayInMinutes, ChronoUnit.MINUTES));

        LOGGER.info("Created message with UUID: {}", message);
        return message;
    }
}