package pl.jsystems.advancedjava.reflection.solutions.s3buildstuff;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

class ReflectionExercise3BuildingStuff
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionExercise3BuildingStuff.class);

    // lookout - this contains full path - for different experiments it can break!
    private static final List<String> classNames = List.of(
            "pl.jsystems.advancedjava.reflection.solutions.s3buildstuff.MessageLogger",
            "pl.jsystems.advancedjava.reflection.solutions.s3buildstuff.message.MessageCreator",
            "pl.jsystems.advancedjava.reflection.solutions.s3buildstuff.LogisticsMessagesApp",
            "pl.jsystems.advancedjava.reflection.solutions.s3buildstuff.receivers.CargoLoadedMessageReceiver",
            "pl.jsystems.advancedjava.reflection.solutions.s3buildstuff.receivers.CargoUnloadedMessageReceiver",
            "pl.jsystems.advancedjava.reflection.solutions.s3buildstuff.receivers.GPSTrackingMessageReceiver",
            "pl.jsystems.advancedjava.reflection.solutions.s3buildstuff.repositories.CargoLoadedMessageRepository",
            "pl.jsystems.advancedjava.reflection.solutions.s3buildstuff.repositories.CargoUnloadedMessageRepository",
            "pl.jsystems.advancedjava.reflection.solutions.s3buildstuff.repositories.GPSTrackingMessageRepository"
    );

    public static void main(String[] args)
    {
        new ReflectionExercise3BuildingStuff().run();
    }

    private void run()
    {
        List<? extends Class<?>> classesToProcess = classNames.stream()
                .map(this::getForName)
                .filter(Objects::nonNull)
                .filter(cls -> cls.getAnnotation(ForDependencyInjection.class) != null || cls.getAnnotation(MainClass.class) != null)
                .collect(Collectors.toList());

        Map<Class<?>, Object> createdObjectsByClass = new HashMap<>();

        boolean classesLeftToCreate = true;
        while (classesLeftToCreate)
        {
            int initialClassListSize = classesToProcess.size();
            classesToProcess.stream()
                    .map(cls -> Arrays.asList(cls.getDeclaredConstructors()))
                    .flatMap(List::stream)
                    .filter(constructor -> Arrays.stream(constructor.getParameterTypes()).allMatch(createdObjectsByClass::containsKey))
                    .forEach(constructor -> buildObject(constructor, createdObjectsByClass));
            //noinspection SuspiciousMethodCalls
            classesToProcess.removeAll(createdObjectsByClass.keySet());
            if (classesToProcess.size() == initialClassListSize)
            {
                classesLeftToCreate = false;
            }
        }

        LOGGER.info("All classes - and created objects: {}", createdObjectsByClass);

        List<Object> mainObjectCandidates = createdObjectsByClass.entrySet()
                .stream()
                .filter(e -> e.getKey().getAnnotation(MainClass.class) != null)
                .map(Map.Entry::getValue)
                .toList();


        LOGGER.info("Starting point candidates: {}", mainObjectCandidates);

        if (mainObjectCandidates.size() > 1) {
            throw new IllegalStateException("More than 1 candidates to start the app!");
        } else if (mainObjectCandidates.size() < 1) {
            throw new IllegalStateException("No candidates found to start the app!");
        }

        Object oneAndOnly = mainObjectCandidates.get(0);
        try
        {
            sleepForABit();
            oneAndOnly.getClass().getDeclaredMethod("run").invoke(oneAndOnly);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
        {
            throw new RuntimeException("Error while trying to run the main application using 'run' " +
                    "method on instance of class " + oneAndOnly.getClass().getName());
        }
    }

    private static void buildObject(Constructor<?> constructor, Map<Class<?>, Object> createdObjectsByClass)
    {
        int numberOfConstructorParams = constructor.getParameterTypes().length;
        Object[] objectToInject = new Object[constructor.getParameterTypes().length];
        for (int i = 0; i < numberOfConstructorParams; i++)
        {
            objectToInject[i] = createdObjectsByClass.get(constructor.getParameterTypes()[i]);
        }
        Object newObject = createNewInstance(constructor, objectToInject);
        createdObjectsByClass.put(constructor.getDeclaringClass(), newObject);
    }

    private static Object createNewInstance(Constructor<?> constructor, Object... params)
    {
        try
        {
            return constructor.newInstance(params);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e)
        {
            LOGGER.warn("Constructor: {}, could not be initialized with: {}", constructor, Arrays.asList(params));
            throw new RuntimeException(e);
        }
    }

    private Class<?> getForName(String name)
    {
        try
        {
            return Class.forName(name);
        } catch (ClassNotFoundException e)
        {
            LOGGER.warn("Your class has not been found.");
            return null;
        }
    }

    private static void sleepForABit()
    {
        try
        {
            Thread.sleep(7000);
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            LOGGER.error("Interrupted!");
            throw new RuntimeException("Interrupted!", e);
        }
    }
}

