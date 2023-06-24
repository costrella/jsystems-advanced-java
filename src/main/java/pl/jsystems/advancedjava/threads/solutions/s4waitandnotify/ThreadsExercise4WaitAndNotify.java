package pl.jsystems.advancedjava.threads.solutions.s4waitandnotify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.random.RandomGenerator;

class ThreadsExercise4WaitAndNotify
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsExercise4WaitAndNotify.class);

    private final Object MY_THREADS_SYNCHRONIZATION_MONITOR = new Object();
    private double notificationValue;

    public static void main(String[] args)
    {
        new ThreadsExercise4WaitAndNotify().run();
    }

    private void run()
    {
        LOGGER.info("Starting infinite loop that generates random numbers");
        Thread listener = new MyThread();
        listener.start();
        while (true)
        {
            double randomValue = RandomGenerator.getDefault().nextDouble(0, 10d);
            synchronized (MY_THREADS_SYNCHRONIZATION_MONITOR)
            {
                notificationValue = randomValue;
                MY_THREADS_SYNCHRONIZATION_MONITOR.notify();
            }
            sleepForABit();
        }
    }

    private class MyThread extends Thread
    {
        @Override
        public void run()
        {
            LOGGER.info("STARTING TO WAIT!");
            while (true)
            {
                double valueToProcess;
                synchronized (MY_THREADS_SYNCHRONIZATION_MONITOR)
                {
                    waitForSomethingToDo();
                    valueToProcess = notificationValue;
                }
                LOGGER.info("NOTIFIED! PROCESSING: {}", valueToProcess);
                LOGGER.info("WAITING AGAIN!");
            }
        }

        private void waitForSomethingToDo()
        {
            try
            {
                MY_THREADS_SYNCHRONIZATION_MONITOR.wait();
            } catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
                LOGGER.error("Interrupted!", e);
                throw new RuntimeException("Interrupted!", e);
            }
        }
    }

    private void sleepForABit()
    {
        try
        {
            Thread.sleep(2000);
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            LOGGER.info("Wait interrupted!", e);
            throw new RuntimeException("Wait interrupted!", e);
        }
    }
}
