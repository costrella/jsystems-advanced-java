package pl.jsystems.advancedjava.threads.exercises.e5lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.random.RandomGenerator;

class ThreadsExercise5WaitAndNotify
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsExercise5WaitAndNotify.class);

    private final Object MY_THREADS_SYNCHRONIZATION_MONITOR = new Object();

    public static void main(String[] args)
    {
        new ThreadsExercise5WaitAndNotify().run();
    }

    private void run()
    {
        LOGGER.info("Starting infinite loop that generates random numbers");
        Thread listener1 = new MyThread(MY_THREADS_SYNCHRONIZATION_MONITOR);
        Thread listener2 = new MyThread(MY_THREADS_SYNCHRONIZATION_MONITOR);
        Thread listener3 = new MyThread(MY_THREADS_SYNCHRONIZATION_MONITOR);
        Thread listener4 = new MyThread(MY_THREADS_SYNCHRONIZATION_MONITOR);
        listener1.start();
        listener2.start();
        listener3.start();
        listener4.start();
        int counter = 0;
        while (counter++ < 50)
        {
            int randomValue = RandomGenerator.getDefault().nextInt(0, 10);
            LOGGER.info("NEW NUMBER: {}", randomValue);
            synchronized (MY_THREADS_SYNCHRONIZATION_MONITOR)
            {
                MY_THREADS_SYNCHRONIZATION_MONITOR.notify();
            }
            sleepForABit(50);
        }
    }

    static void sleepForABit(int time)
    {
        try
        {
            Thread.sleep(time);
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            LOGGER.info("Wait interrupted!", e);
            throw new RuntimeException("Wait interrupted!", e);
        }
    }
}
