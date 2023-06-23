package pl.jsystems.advancedjava.threads.solutions.s1creatingthreads.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.threads.solutions.s1creatingthreads.MessageRepository;
import pl.jsystems.advancedjava.threads.solutions.s1creatingthreads.contents.CargoLoadedMessageContent;
import pl.jsystems.advancedjava.threads.solutions.s1creatingthreads.message.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.random.RandomGenerator;

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
    public Optional<Message<CargoLoadedMessageContent>> findById(UUID id)
    {
        return Optional.ofNullable(RandomGenerator.getDefault().nextInt(2) == 0 ? MESSAGES.get(id) : null);
    }

    @Override
    public void save(Message<CargoLoadedMessageContent> message)
    {
        LOGGER.info("Saving cargo loading message: {}", message);
        MESSAGES.put(message.id(), message);
        ALL_MESSAGES.add(message);
    }
}
