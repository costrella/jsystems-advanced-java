package pl.jsystems.advancedjava.reflection.exercises.e4readfromfile.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.reflection.exercises.e4readfromfile.ForDependencyInjection;
import pl.jsystems.advancedjava.reflection.exercises.e4readfromfile.MessageRepository;
import pl.jsystems.advancedjava.reflection.exercises.e4readfromfile.contents.CargoUnloadedMessageContent;
import pl.jsystems.advancedjava.reflection.exercises.e4readfromfile.message.Message;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@ForDependencyInjection
public class CargoUnloadedMessageRepository implements MessageRepository<CargoUnloadedMessageContent>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CargoUnloadedMessageRepository.class);

    private static final Map<UUID, Message<CargoUnloadedMessageContent>> MESSAGES = new ConcurrentHashMap<>();

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
        LOGGER.info("Saving cargo unloading message: {}", message.id());
        MESSAGES.put(message.id(), message);
    }
}