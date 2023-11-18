package pl.jsystems.advancedjava.reflection.solutions.s3buildstuff.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.reflection.solutions.s3buildstuff.ForDependencyInjection;
import pl.jsystems.advancedjava.reflection.solutions.s3buildstuff.MessageRepository;
import pl.jsystems.advancedjava.reflection.solutions.s3buildstuff.contents.GPSTrackingMessageContent;
import pl.jsystems.advancedjava.reflection.solutions.s3buildstuff.message.Message;

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