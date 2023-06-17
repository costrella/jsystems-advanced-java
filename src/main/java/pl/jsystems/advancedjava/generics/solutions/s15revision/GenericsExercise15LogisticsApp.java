package pl.jsystems.advancedjava.generics.solutions.s15revision;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.generics.solutions.s15revision.contents.GPSTrackingMessageContent;
import pl.jsystems.advancedjava.generics.solutions.s15revision.message.Message;

class GenericsExercise15LogisticsApp
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericsExercise15LogisticsApp.class);

    public static void main(String[] args)
    {
        new GenericsExercise15LogisticsApp().run();
    }

    private void run()
    {
        LOGGER.info("Time to receive, store and read some messages!");
        MessageLogger messageLogger = new MessageLogger();
        MessageReceiver<GPSTrackingMessageContent> gpsMessagesReceiver = new GPSTrackingMessageReceiver();
        MessageRepository<GPSTrackingMessageContent> gpsMessageRepository = new GPSTrackingMessageRepository();

        Message<GPSTrackingMessageContent> message1 = gpsMessagesReceiver.getLatestReceivedMessage();
        messageLogger.logReceived(message1);
        gpsMessageRepository.save(message1);
        LOGGER.info("Saved message: {}", message1);

        Message<GPSTrackingMessageContent> message2 = gpsMessagesReceiver.getLatestReceivedMessage();
        messageLogger.logReceived(message2);
        gpsMessageRepository.save(message2);
        LOGGER.info("Saved message: {}", message2);

        var readMessage = gpsMessageRepository.findById(message2.id());
        LOGGER.info("Found message by id - {}: {}", message2.id(), readMessage);
        var allMessages = gpsMessageRepository.findAll();
        LOGGER.info("Found all messages: {}", allMessages);
    }

}
