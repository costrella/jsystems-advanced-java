package pl.jsystems.advancedjava.generics.solutions.s15revision;

import pl.jsystems.advancedjava.generics.solutions.s15revision.contents.GPSTrackingMessageContent;
import pl.jsystems.advancedjava.generics.solutions.s15revision.message.Message;
import pl.jsystems.advancedjava.generics.solutions.s15revision.message.MessageCreator;

class GPSTrackingMessageReceiver implements MessageReceiver<GPSTrackingMessageContent>
{
    private final MessageCreator messageCreator = new MessageCreator();

    @Override
    public Message<GPSTrackingMessageContent> getLatestReceivedMessage()
    {
        return messageCreator.createMessageUsing(new GPSTrackingMessageContent());
    }
}
