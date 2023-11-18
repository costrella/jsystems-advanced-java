package pl.jsystems.advancedjava.threads.solutions.s5lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.random.RandomGenerator;

class ThreadsExercise5WaitAndNotify
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsExercise5WaitAndNotify.class);

    private final Lock lock = new ReentrantLock();

    public static void main(String[] args)
    {
        new ThreadsExercise5WaitAndNotify().run();
    }

    private void run()
    {
        LOGGER.info("Starting infinite loop that generates random numbers");
        ValueHolder valueHolder = new ValueHolder(0);
        Condition lessThan6 = lock.newCondition();
        Condition moreThan5 = lock.newCondition();
        Thread listener1 = new MyThread(lock, lessThan6, valueHolder);
        Thread listener2 = new MyThread(lock, lessThan6, valueHolder);
        Thread listener3 = new MyThread(lock, moreThan5, valueHolder);
        Thread listener4 = new MyThread(lock, moreThan5, valueHolder);
        listener1.start();
        listener2.start();
        listener3.start();
        listener4.start();
        int counter = 0;
        while (counter++ < 50)
        {
            int randomValue = RandomGenerator.getDefault().nextInt(0, 10);
            LOGGER.info("NEW NUMBER: {}", randomValue);
            lock.lock();
            try
            {
                if (randomValue < 6)
                {
                    lessThan6.signal();
                }
                else
                {
                    moreThan5.signal();
                }
                valueHolder.setValue(randomValue);
            } finally
            {
                lock.unlock();
            }
            sleepForABit(50);
        }
        sleepForABit(2000);
        listener1.interrupt();
        listener2.interrupt();
        listener3.interrupt();
        listener4.interrupt();
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

    static class ValueHolder
    {
        private Integer value;

        ValueHolder(Integer value)
        {
            this.value = value;
        }

        Integer getValue()
        {
            return value;
        }

        void setValue(Integer value)
        {
            this.value = value;
        }
    }
}