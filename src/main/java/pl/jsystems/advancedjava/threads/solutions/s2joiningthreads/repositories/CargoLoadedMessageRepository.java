package pl.jsystems.advancedjava.threads.solutions.s2joiningthreads.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.threads.solutions.s2joiningthreads.MessageRepository;
import pl.jsystems.advancedjava.threads.solutions.s2joiningthreads.contents.CargoLoadedMessageContent;
import pl.jsystems.advancedjava.threads.solutions.s2joiningthreads.message.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class CargoLoadedMessageRepository implements MessageRepository<CargoLoadedMessageContent>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CargoLoadedMessageContent.class);

    private static final Map<UUID, Message<CargoLoadedMessageContent>> MESSAGES = new HashMap<>();

    @Override
    public List<Message<CargoLoadedMessageContent>> findAll()
    {
        return List.copyOf(MESSAGES.values());
    }

    @Override
    public Optional<Message<CargoLoadedMessageContent>> findById(UUID id)
    {
        return Optional.ofNullable(MESSAGES.get(id));
    }

    @Override
    public void save(Message<CargoLoadedMessageContent> message)
    {
        LOGGER.info("Saving cargo loading message: {}", message);
        MESSAGES.put(message.id(), message);
    }
}