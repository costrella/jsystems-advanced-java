package pl.jsystems.advancedjava.lambdas.exercises.e2anonymousclasses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.lambdas.exercises.e2anonymousclasses.contents.GPSTrackingMessageContent;
import pl.jsystems.advancedjava.lambdas.exercises.e2anonymousclasses.message.Message;

import java.util.function.Consumer;

class LambdasExercise2AnonymousClasses
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LambdasExercise2AnonymousClasses.class);

    public static void main(String[] args)
    {
        new LambdasExercise2AnonymousClasses().run();
    }

    private void run()
    {
        LOGGER.info("Time to consume some messages!");
        MessageReceiver<GPSTrackingMessageContent> gpsMessagesReceiver = new GPSTrackingMessageReceiver();
        MessageLogger messageLogger = new MessageLogger();
        GPSTrackingMessageRepository gpsMessageRepository = new GPSTrackingMessageRepository();
        GpsMessageConsumer messageConsumer = new GpsMessageConsumer(messageLogger, gpsMessageRepository);
        gpsMessagesReceiver.startReceivingUsing(messageConsumer);
    }

    static class GpsMessageConsumer implements Consumer<Message<GPSTrackingMessageContent>>
    {
        private final MessageLogger messageLogger;
        private final MessageRepository<GPSTrackingMessageContent> gpsMessageRepository;

        GpsMessageConsumer(MessageLogger messageLogger, MessageRepository<GPSTrackingMessageContent> gpsMessageRepository)
        {
            this.messageLogger = messageLogger;
            this.gpsMessageRepository = gpsMessageRepository;
        }

        @Override
        public void accept(Message<GPSTrackingMessageContent> gpsTrackingMessage)
        {
            messageLogger.logReceived(gpsTrackingMessage);
            gpsMessageRepository.save(gpsTrackingMessage);
            LOGGER.info("Saved message: {}", gpsTrackingMessage);
        }
    }
}
