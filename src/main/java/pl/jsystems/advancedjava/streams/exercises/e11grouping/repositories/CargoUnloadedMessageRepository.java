package pl.jsystems.advancedjava.streams.exercises.e11grouping.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.streams.exercises.e11grouping.MessageRepository;
import pl.jsystems.advancedjava.streams.exercises.e11grouping.contents.CargoUnloadedMessageContent;
import pl.jsystems.advancedjava.streams.exercises.e11grouping.message.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class CargoUnloadedMessageRepository implements MessageRepository<CargoUnloadedMessageContent>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CargoUnloadedMessageContent.class);

    private static final Map<UUID, Message<CargoUnloadedMessageContent>> MESSAGES = new HashMap<>();

    @Override
    public List<Message<CargoUnloadedMessageContent>> findAll()
    {
        return List.copyOf(MESSAGES.values());
    }

    @Override
    public Optional<Message<CargoUnloadedMessageContent>> findById(UUID id)
    {
        return Optional.ofNullable(MESSAGES.get(id));
    }

    @Override
    public void save(Message<CargoUnloadedMessageContent> message)
    {
        LOGGER.info("Saving cargo loading message: {}", message);
        MESSAGES.put(message.id(), message);
    }
}