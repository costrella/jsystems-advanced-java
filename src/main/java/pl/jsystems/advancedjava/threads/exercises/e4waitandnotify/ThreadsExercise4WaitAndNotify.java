package pl.jsystems.advancedjava.threads.exercises.e4waitandnotify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.random.RandomGenerator;

class ThreadsExercise4WaitAndNotify
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsExercise4WaitAndNotify.class);

    private final Object MY_THREADS_SYNCHRONIZATION_MONITOR = new Object();

    public static void main(String[] args)
    {
        new ThreadsExercise4WaitAndNotify().run();
    }

    private void run()
    {
        LOGGER.info("Starting infinite loop that generates random numbers");
        while (true)
        {
            double randomValue = RandomGenerator.getDefault().nextDouble(0, 10d);
            synchronized (MY_THREADS_SYNCHRONIZATION_MONITOR)
            {
                MY_THREADS_SYNCHRONIZATION_MONITOR.notify();
            }
            sleepForABit();
        }
    }

    private void sleepForABit()
    {
        try
        {
            Thread.sleep(1000);
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            LOGGER.error("Wait interrupted!", e);
            throw new RuntimeException("Wait interrupted!", e);
        }
    }
}
