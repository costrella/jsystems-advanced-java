package pl.jsystems.advancedjava.lambdas.exercises.e8functioninterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class LambdasExercise8FunctionInterface
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LambdasExercise8FunctionInterface.class);

    public static void main(String[] args)
    {
        new LambdasExercise8FunctionInterface().run();
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
