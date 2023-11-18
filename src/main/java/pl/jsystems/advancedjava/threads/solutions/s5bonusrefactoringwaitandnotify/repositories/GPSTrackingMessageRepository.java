package pl.jsystems.advancedjava.threads.solutions.s5bonusrefactoringwaitandnotify.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.threads.solutions.s5bonusrefactoringwaitandnotify.MessageRepository;
import pl.jsystems.advancedjava.threads.solutions.s5bonusrefactoringwaitandnotify.contents.GPSTrackingMessageContent;
import pl.jsystems.advancedjava.threads.solutions.s5bonusrefactoringwaitandnotify.message.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
    public Optional<Message<GPSTrackingMessageContent>> findById(UUID id)
    {
        return Optional.ofNullable(MESSAGES.get(id));
    }

    @Override
    public void save(Message<GPSTrackingMessageContent> message)
    {
        LOGGER.info("Saving GPS message: {}", message);
        MESSAGES.put(message.id(), message);
    }
}