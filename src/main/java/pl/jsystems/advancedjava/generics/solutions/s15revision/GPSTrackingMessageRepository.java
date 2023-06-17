package pl.jsystems.advancedjava.generics.solutions.s15revision;

import pl.jsystems.advancedjava.generics.solutions.s15revision.contents.GPSTrackingMessageContent;
import pl.jsystems.advancedjava.generics.solutions.s15revision.message.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

class GPSTrackingMessageRepository implements MessageRepository<GPSTrackingMessageContent>
{
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
        MESSAGES.put(message.id(), message);
    }
}
