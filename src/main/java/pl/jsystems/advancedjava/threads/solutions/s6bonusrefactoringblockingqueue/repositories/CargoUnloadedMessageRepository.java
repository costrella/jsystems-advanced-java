package pl.jsystems.advancedjava.threads.solutions.s6bonusrefactoringblockingqueue.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.threads.solutions.s6bonusrefactoringblockingqueue.contents.CargoUnloadedMessageContent;
import pl.jsystems.advancedjava.threads.solutions.s6bonusrefactoringblockingqueue.message.Message;
import pl.jsystems.advancedjava.threads.solutions.s6bonusrefactoringblockingqueue.MessageRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class CargoUnloadedMessageRepository implements MessageRepository<CargoUnloadedMessageContent>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CargoUnloadedMessageRepository.class);

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
        LOGGER.info("Saving cargo unloading message: {}", message.id());
        MESSAGES.put(message.id(), message);
    }
}
