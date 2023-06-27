package pl.jsystems.advancedjava.reflection.examples.e2evil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

class ReflectionExample2Classes
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionExample2Classes.class);

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException
    {
        new ReflectionExample2Classes().run();
    }

    // we should handle those exceptions, for sake of clarity we don't do that here though!
    private void run() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException
    {
        Arrays.asList(GuineaPig.class.getDeclaredConstructors())
                .forEach(i -> LOGGER.info("{}", i));

        Constructor<GuineaPig> constructor = GuineaPig.class
                .getDeclaredConstructor(String.class, int.class, int.class);
        LOGGER.info("Chosen one: " + constructor);

        GuineaPig guineaPig = constructor.newInstance("Piggy", 0, 20);
        LOGGER.info("My new piggy! {}", guineaPig);

        constructor = GuineaPig.class
                .getDeclaredConstructor(String.class, int.class);
        LOGGER.info("Chose other constructor: " + constructor);
        constructor.setAccessible(true);
        GuineaPig shyPig = constructor.newInstance("ShyPig", 20);
        LOGGER.info("My new shy piggy (private constructor)! {}", shyPig);

        Arrays.stream(GuineaPig.class.getDeclaredMethods())
                .filter(method -> method.getName().equals("setSleeping"))
                .findFirst()
                .ifPresent(method -> {
                    try
                    {
                        method.invoke(shyPig, true);
                    } catch (IllegalAccessException | InvocationTargetException e)
                    {
                        throw new RuntimeException(e);
                    }
                });
        LOGGER.info("Look, it's asleep! {}", shyPig);

        Arrays.stream(GuineaPig.class.getDeclaredFields())
                .filter(field -> field.getName().equals("isHungry"))
                .findFirst()
                .ifPresent(field -> {
                    try
                    {
                        // try to comment it out and see what happens!
                        field.setAccessible(true);
                        field.set(shyPig, true);
                    } catch (IllegalAccessException e)
                    {
                        throw new RuntimeException(e);
                    }
                });
        LOGGER.info("And now it's hungry! {}", shyPig);

        Arrays.stream(GuineaPig.class.getDeclaredFields())
                .filter(field -> field.getName().equals("name"))
                .findFirst()
                .ifPresent(field -> {
                    try
                    {
                        // try to comment it out and see what happens!
                        field.setAccessible(true);
                        field.set(shyPig, "HUNGRY SLEEPY PIGGY");
                    } catch (IllegalAccessException e)
                    {
                        throw new RuntimeException(e);
                    }
                });
        LOGGER.info("Wow, just wow! {}", shyPig);
    }
}

