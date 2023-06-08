package pl.jsystems.advancedjava.generics.exercises.e2genericmessageid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class GenericsExercise2GenericMessageId
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericsExercise2GenericMessageId.class);

    public static void main(String[] args)
    {
        new GenericsExercise2GenericMessageId().run();
    }

    private void run()
    {
        LOGGER.info("Creating new message with id...");
    }
}
