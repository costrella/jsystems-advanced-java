package pl.jsystems.advancedjava.reflection.solutions.s1basics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

class ReflectionExercise1Basics
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionExercise1Basics.class);

    public static void main(String[] args)
    {
        LOGGER.info("Canonical name: {}", LogisticsMessagesApp.class.getCanonicalName());

        Field[] publicFields = LogisticsMessagesApp.class.getFields();
        LOGGER.info("Listing (public) fields");
        for (Field field : publicFields)
        {
            LOGGER.info("Field info: " + field);
        }

        Field[] declaredFields = LogisticsMessagesApp.class.getDeclaredFields();
        LOGGER.info("Listing (declared) fields");
        for (Field field : declaredFields)
        {
            LOGGER.info("Field info: " + field);
        }

        Method[] publicMethods = LogisticsMessagesApp.class.getMethods();
        LOGGER.info("Listing (public) methods");
        for (Method method : publicMethods)
        {
            LOGGER.info("Method info: " + method);
        }

        Method[] declaredMethods = LogisticsMessagesApp.class.getDeclaredMethods();
        LOGGER.info("Listing (declared) methods");
        for (Method method : declaredMethods)
        {
            LOGGER.info("Method info: " + method);
        }

        LOGGER.info(" {} extends from Object, right? {}",
                LogisticsMessagesApp.class.getSimpleName(), Object.class.isAssignableFrom(LogisticsMessagesApp.class));
    }
}

