package pl.jsystems.advancedjava.lambdas.exercises.e9genericsrevision;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class LambdasExercise9GenericsRevision
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LambdasExercise9GenericsRevision.class);

    public static void main(String[] args)
    {
        new LambdasExercise9GenericsRevision().run();
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
