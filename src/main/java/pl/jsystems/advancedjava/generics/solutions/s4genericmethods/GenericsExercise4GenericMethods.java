package pl.jsystems.advancedjava.generics.solutions.s4genericmethods;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericsExercise4GenericMethods
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericsExercise4GenericMethods.class);

    public static void main(String[] args)
    {
        new GenericsExercise4GenericMethods().run();
    }

    private void run()
    {
        LOGGER.info("Creating new message using creator...");
        String firstContent = "First content";
        String secondContent = "Second content";
        String thirdContent = "Third content";

        Box<String> box = new Box<>();
        box.putInto(firstContent);
        LOGGER.info("I have a box! {}", box);

        BoxContentReplacer contentReplacer = new BoxContentReplacer();
        contentReplacer.replaceUsing(box, secondContent, "me");
        LOGGER.info("I have a box with new content! {}", box);

        String oldNewContent = contentReplacer.replaceUsing(box, thirdContent, "myself");
        LOGGER.info("And I now replaced that content {} with an even newer one! {}", oldNewContent, box);
    }
}
