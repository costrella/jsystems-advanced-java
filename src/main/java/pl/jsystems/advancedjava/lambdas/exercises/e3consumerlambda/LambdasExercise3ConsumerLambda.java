package pl.jsystems.advancedjava.lambdas.exercises.e3consumerlambda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.lambdas.exercises.e3consumerlambda.contents.GPSTrackingMessageContent;
import pl.jsystems.advancedjava.lambdas.exercises.e3consumerlambda.message.Message;

import java.util.function.Consumer;

class LambdasExercise3ConsumerLambda
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LambdasExercise3ConsumerLambda.class);

    public static void main(String[] args)
    {
        new LambdasExercise3ConsumerLambda().run();
    }

    private void run()
    {
        LOGGER.info("Time to consume some messages!");
        MessageLogger messageLogger = new MessageLogger();
        GPSTrackingMessageRepository gpsMessageRepository = new GPSTrackingMessageRepository();

        new GPSTrackingMessageReceiver().startReceivingUsing(new Consumer<Message<GPSTrackingMessageContent>>()
        {
            @Override
            public void accept(Message<GPSTrackingMessageContent> message)
            {
                messageLogger.logReceived(message);
                gpsMessageRepository.save(message);
                LOGGER.info("Saved message: {}", message);
            }
        });
    }
}
