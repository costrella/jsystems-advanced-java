package pl.jsystems.advancedjava.generics.exercises.e1genericmessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class GenericsExercise1GenericMessage
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericsExercise1GenericMessage.class);

    public static void main(String[] args)
    {
        new GenericsExercise1GenericMessage().run();
    }

    private void run()
    {
        LOGGER.info("Creating new message...");
    }
}
