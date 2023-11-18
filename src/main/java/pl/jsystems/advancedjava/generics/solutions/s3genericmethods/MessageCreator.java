package pl.jsystems.advancedjava.generics.solutions.s3genericmethods;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

class MessageCreator
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageCreator.class);

    <ID, CONTENT> Message<ID, CONTENT> createMessageUsing(ID id, CONTENT content)
    {
        Message<ID, CONTENT> message = new Message<>(id, content);
        LOGGER.info("Created message: {}", message);
        return message;
    }

    <CONTENT> Message<UUID, CONTENT> createMessageUsing(CONTENT content)
    {
        Message<UUID, CONTENT> message = new Message<>(UUID.randomUUID(), content);
        LOGGER.info("Created message with UUID: {}", message);
        return message;
    }
}