package pl.jsystems.advancedjava.lambdas.exercises.e6biconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class LambdasExercise6BiConsumer
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LambdasExercise6BiConsumer.class);

    public static void main(String[] args)
    {
        new LambdasExercise6BiConsumer().run();
    }

    private void run()
    {
        LOGGER.info("Time to consume some messages!");
        MessageLogger messageLogger = new MessageLogger();
        GPSTrackingMessageRepository gpsMessageRepository = new GPSTrackingMessageRepository();

        new GPSTrackingMessageReceiver().startReceivingUsing(message ->
        {
            messageLogger.logReceived(message);
            gpsMessageRepository.save(message);
            LOGGER.info("Saved message: {}", message);
        });
    }
}
