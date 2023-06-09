package pl.jsystems.advancedjava.generics.exercises.e3genericmethods;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

class GenericsExercise3GenericMethods
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericsExercise3GenericMethods.class);

    public static void main(String[] args)
    {
        new GenericsExercise3GenericMethods().run();
    }

    private void run()
    {
        LOGGER.info("Creating new message using creator...");
        /*
        MessageCreator messageCreator = new MessageCreator();
        messageCreator.createMessageUsing(1L, "my super content");
        messageCreator.createMessageUsing("second!", "my other content");
        messageCreator.createMessageUsing("content with super id!");
        messageCreator.createMessageUsing(3d, 42);
        messageCreator.createMessageUsing(BigDecimal.ZERO);
        */
    }
}
