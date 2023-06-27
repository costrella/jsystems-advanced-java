package pl.jsystems.advancedjava.threads.solutions.s5lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import static pl.jsystems.advancedjava.threads.solutions.s5lock.ThreadsExercise5WaitAndNotify.sleepForABit;


class MyThread extends Thread
{
    private final Lock lock;
    private final Condition condition;
    private final ThreadsExercise5WaitAndNotify.ValueHolder valueHolder;
    private static final Logger LOGGER = LoggerFactory.getLogger(MyThread.class);

    public MyThread(Lock lock, Condition condition, ThreadsExercise5WaitAndNotify.ValueHolder valueHolder)
    {
        this.lock = lock;
        this.condition = condition;
        this.valueHolder = valueHolder;
    }

    @Override
    public void run()
    {
        LOGGER.info("STARTING TO WAIT!");
        while (true)
        {
            int valueToProcess;

            waitForSomethingToDo();
            valueToProcess = valueHolder.getValue();
            LOGGER.info("PROCESSING NEW EVENT {}! (SLEEPING)", valueToProcess);
            sleepForABit(300);
        }
    }

    private void waitForSomethingToDo()
    {
        lock.lock();
        try
        {
            condition.await();
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            LOGGER.error("Interrupted!", e);
            throw new RuntimeException("Interrupted!", e);
        } finally
        {
            lock.unlock();
        }
    }
}
