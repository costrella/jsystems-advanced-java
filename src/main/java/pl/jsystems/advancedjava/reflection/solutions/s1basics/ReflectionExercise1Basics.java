package pl.jsystems.advancedjava.reflection.solutions.s1basics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.reflection.solutions.s1basics.message.Message;
import pl.jsystems.advancedjava.reflection.solutions.s1basics.message.SampleConcreteMessage;

import java.util.Arrays;

class ReflectionExercise1Basics
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionExercise1Basics.class);

    public static void main(String[] args)
    {
        LOGGER.info("Canonical name: {}", LogisticsMessagesApp.class.getCanonicalName());

        LOGGER.info("Listing (public) field");
        Arrays.asList(Message.class.getFields())
                .forEach(field -> LOGGER.info("Method info: " + field));

        LOGGER.info("Listing (declared) fields");
        Arrays.asList(Message.class.getDeclaredFields())
                .forEach(field -> LOGGER.info("Field info: " + field));

        LOGGER.info("Listing (declared) fields - SAMPLE CONCRETE MESSAGE");

        Arrays.asList(SampleConcreteMessage.class.getGenericSuperclass())
                .forEach(field -> LOGGER.info("Field info: " + field));

        LOGGER.info("Listing (declared) fields");
        Arrays.asList(Message.class.getDeclaredFields())
                .forEach(field -> LOGGER.info("Field info: " + field));

        LOGGER.info("Listing (public) methods");
        Arrays.asList(Message.class.getMethods())
                .forEach(method -> LOGGER.info("Method info: " + method));

        LOGGER.info("Listing (declared) methods");
        Arrays.asList(Message.class.getDeclaredMethods())
                .forEach(method -> LOGGER.info("Method info: " + method));

        LOGGER.info(" {} extends from Object, right? {}",
                Message.class.getSimpleName(),
                Object.class.isAssignableFrom(LogisticsMessagesApp.class));
    }
}

