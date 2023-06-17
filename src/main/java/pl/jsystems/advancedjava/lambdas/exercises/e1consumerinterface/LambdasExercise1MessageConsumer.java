package pl.jsystems.advancedjava.lambdas.exercises.e1consumerinterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.lambdas.exercises.e1consumerinterface.contents.GPSTrackingMessageContent;
import pl.jsystems.advancedjava.lambdas.exercises.e1consumerinterface.message.Message;

class LambdasExercise1MessageConsumer
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LambdasExercise1MessageConsumer.class);

    public static void main(String[] args)
    {
        new LambdasExercise1MessageConsumer().run();
    }

    private void run()
    {
        LOGGER.info("Time to consume some messages!");
        MessageReceiver<GPSTrackingMessageContent> gpsMessagesReceiver = new GPSTrackingMessageReceiver();
        Message<GPSTrackingMessageContent> message1 = gpsMessagesReceiver.getLatestReceivedMessage();

        // TODO: Move this to consumer.
        MessageLogger messageLogger = new MessageLogger();
        MessageRepository<GPSTrackingMessageContent> gpsMessageRepository = new GPSTrackingMessageRepository();
        messageLogger.logReceived(message1);
        gpsMessageRepository.save(message1);
        LOGGER.info("Saved message: {}", message1);
    }
}
