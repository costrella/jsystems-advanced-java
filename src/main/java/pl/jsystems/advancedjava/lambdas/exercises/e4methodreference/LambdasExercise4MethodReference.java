package pl.jsystems.advancedjava.lambdas.exercises.e4methodreference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class LambdasExercise4MethodReference
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LambdasExercise4MethodReference.class);

    public static void main(String[] args)
    {
        new LambdasExercise4MethodReference().run();
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

        new GPSTrackingMessageReceiver().startReceivingUsing(message -> gpsMessageRepository.save(message));
    }
}
