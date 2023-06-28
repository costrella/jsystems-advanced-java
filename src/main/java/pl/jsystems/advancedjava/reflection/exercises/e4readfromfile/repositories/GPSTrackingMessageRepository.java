package pl.jsystems.advancedjava.reflection.exercises.e4readfromfile.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.reflection.exercises.e4readfromfile.ForDependencyInjection;
import pl.jsystems.advancedjava.reflection.exercises.e4readfromfile.contents.GPSTrackingMessageContent;
import pl.jsystems.advancedjava.reflection.exercises.e4readfromfile.message.Message;
import pl.jsystems.advancedjava.reflection.exercises.e4readfromfile.MessageRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@ForDependencyInjection
public class GPSTrackingMessageRepository implements MessageRepository<GPSTrackingMessageContent>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GPSTrackingMessageRepository.class);

    private static final Map<UUID, Message<GPSTrackingMessageContent>> MESSAGES = new ConcurrentHashMap<>();

    @Override
    public List<Message<GPSTrackingMessageContent>> findAll()
    {
        return List.copyOf(MESSAGES.values());
    }

    @Override
    public Optional<Message<GPSTrackingMessageContent>> findById(UUID id)
    {
        return Optional.ofNullable(MESSAGES.get(id));
    }

    @Override
    public void save(Message<GPSTrackingMessageContent> message)
    {
        LOGGER.info("Saving GPS message: {}", message.id());
        MESSAGES.put(message.id(), message);
    }
}
