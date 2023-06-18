package pl.jsystems.advancedjava.lambdas.solutions.s7lambdasandgenerics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class LambdasExercise7LambdasAndGenerics
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LambdasExercise7LambdasAndGenerics.class);

    public static void main(String[] args)
    {
        new LambdasExercise7LambdasAndGenerics().run();
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
