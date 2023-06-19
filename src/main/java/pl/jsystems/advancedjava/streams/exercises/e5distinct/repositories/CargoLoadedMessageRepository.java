package pl.jsystems.advancedjava.streams.exercises.e5distinct.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.streams.exercises.e5distinct.MessageRepository;
import pl.jsystems.advancedjava.streams.exercises.e5distinct.message.Message;
import pl.jsystems.advancedjava.streams.exercises.e5distinct.contents.CargoLoadedMessageContent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CargoLoadedMessageRepository implements MessageRepository<CargoLoadedMessageContent>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CargoLoadedMessageContent.class);

    private static final Map<UUID, Message<CargoLoadedMessageContent>> MESSAGES = new HashMap<>();
    // just for distinct testing - allowing for duplicates
    private static final List<Message<CargoLoadedMessageContent>> ALL_MESSAGES = new ArrayList<>();

    @Override
    public List<Message<CargoLoadedMessageContent>> findAll()
    {
        return List.copyOf(ALL_MESSAGES);
    }

    @Override
    public Message<CargoLoadedMessageContent> findById(UUID id)
    {
        return MESSAGES.get(id);
    }

    @Override
    public void save(Message<CargoLoadedMessageContent> message)
    {
        LOGGER.info("Saving cargo loading message: {}", message);
        MESSAGES.put(message.id(), message);
        ALL_MESSAGES.add(message);
    }
}
