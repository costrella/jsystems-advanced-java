package pl.jsystems.advancedjava.streams.solutions.s5distinct.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.streams.solutions.s5distinct.MessageRepository;
import pl.jsystems.advancedjava.streams.solutions.s5distinct.contents.GPSTrackingMessageContent;
import pl.jsystems.advancedjava.streams.solutions.s5distinct.message.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class GPSTrackingMessageRepository implements MessageRepository<GPSTrackingMessageContent>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GPSTrackingMessageRepository.class);

    private static final Map<UUID, Message<GPSTrackingMessageContent>> MESSAGES = new HashMap<>();

    @Override
    public List<Message<GPSTrackingMessageContent>> findAll()
    {
        return List.copyOf(MESSAGES.values());
    }

    @Override
    public Message<GPSTrackingMessageContent> findById(UUID id)
    {
        return MESSAGES.get(id);
    }

    @Override
    public void save(Message<GPSTrackingMessageContent> message)
    {
        LOGGER.info("Saving GPS message: {}", message);
        MESSAGES.put(message.id(), message);
    }
}