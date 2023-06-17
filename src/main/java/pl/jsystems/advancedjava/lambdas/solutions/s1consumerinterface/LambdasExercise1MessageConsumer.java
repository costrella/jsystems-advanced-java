package pl.jsystems.advancedjava.lambdas.solutions.s1consumerinterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.lambdas.solutions.s1consumerinterface.contents.GPSTrackingMessageContent;
import pl.jsystems.advancedjava.lambdas.solutions.s1consumerinterface.contents.MessageContent;
import pl.jsystems.advancedjava.lambdas.solutions.s1consumerinterface.message.Message;

import java.util.function.Consumer;

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
        MessageLogger messageLogger = new MessageLogger();
        GPSTrackingMessageRepository gpsMessageRepository = new GPSTrackingMessageRepository();
        GpsMessageConsumer<GPSTrackingMessageContent> messageConsumer = new GpsMessageConsumer<>(messageLogger, gpsMessageRepository);
        gpsMessagesReceiver.startReceivingUsing(messageConsumer);
    }

    static class GpsMessageConsumer<T extends MessageContent> implements Consumer<Message<T>>
    {
        private final MessageLogger messageLogger;
        private final MessageRepository<T> gpsMessageRepository;

        GpsMessageConsumer(MessageLogger messageLogger, MessageRepository<T> gpsMessageRepository)
        {
            this.messageLogger = messageLogger;
            this.gpsMessageRepository = gpsMessageRepository;
        }

        @Override
        public void accept(Message<T> gpsTrackingMessage)
        {
            messageLogger.logReceived(gpsTrackingMessage);
            gpsMessageRepository.save(gpsTrackingMessage);
            LOGGER.info("Saved message: {}", gpsTrackingMessage);
        }
    }
}
