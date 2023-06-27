package pl.jsystems.advancedjava.threads.exercises.e5lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static pl.jsystems.advancedjava.threads.exercises.e5lock.ThreadsExercise5WaitAndNotify.sleepForABit;

class MyThread extends Thread
{
    private final Object monitor;
    private static final Logger LOGGER = LoggerFactory.getLogger(MyThread.class);

    public MyThread(Object monitor)
    {
        this.monitor = monitor;
    }

    @Override
    public void run()
    {
        LOGGER.info("STARTING TO WAIT!");
        while (true)
        {
            int valueToProcess;
            synchronized (monitor)
            {
                waitForSomethingToDo();
                valueToProcess = 5; // todo
            }
            //LOGGER.info("NOTIFIED! WAITING: {}", valueToProcess); //todo
            LOGGER.info("PROCESSING NEW EVENT! (SLEEPING)");
            sleepForABit(300);
        }
    }

    private void waitForSomethingToDo()
    {
        try
        {
            monitor.wait();
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            LOGGER.error("Interrupted!", e);
            throw new RuntimeException("Interrupted!", e);
        }
    }
}
