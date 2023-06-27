package pl.jsystems.advancedjava.reflection.solutions.s2breakingstuff;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

class ReflectionExercise2BreakingStuff
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionExercise2BreakingStuff.class);

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException
    {
        var testObject = LogisticsMessagesApp.class.getDeclaredConstructor().newInstance();

        var field = LogisticsMessagesApp.class.getDeclaredField("messageLogger");
        field.setAccessible(true);
        field.set(testObject, null);

        var method = LogisticsMessagesApp.class.getDeclaredMethod("run", String.class);
        method.invoke(testObject, "Expect some red logs!");
    }
}

