package pl.jsystems.advancedjava.threads.examples.e11deadlocks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ThreadsExample11Deadlocks
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsExample11Deadlocks.class);

    private final Object MONITOR_1 = new Object();
    private final Object MONITOR_2 = new Object();

    public static void main(String[] args)
    {
        new ThreadsExample11Deadlocks().run();
    }

    private void run()
    {
        LOGGER.info("Blocked!");

        Thread myFancyThread0 = new MyWorkerThread1();
        LOGGER.info("Created new thread 0.");
        myFancyThread0.start();
        LOGGER.info("Thread 0 started.");
        Thread myFancyThread1 = new MyWorkerThread2();
        LOGGER.info("Created new thread 1.");
        myFancyThread1.start();

        LOGGER.info("Thread 0 state: {}, interrupted: {}", myFancyThread0.getState(), myFancyThread0.isInterrupted());
        LOGGER.info("Thread 1 state: {}, interrupted: {}", myFancyThread1.getState(), myFancyThread1.isInterrupted());
        sleepForAWhile();
        myFancyThread0.interrupt();
        myFancyThread1.interrupt();
        sleepForAWhile();
        LOGGER.info("Thread 0 state: {}, interrupted: {}", myFancyThread0.getState(), myFancyThread0.isInterrupted());
        LOGGER.info("Thread 1 state: {}, interrupted: {}", myFancyThread1.getState(), myFancyThread1.isInterrupted());
    }

    private class MyWorkerThread1 extends Thread
    {

        @Override
        public void run()
        {
            LOGGER.info("Thread 1 waiting for MONITOR_1");
            synchronized (MONITOR_1)
            {
                LOGGER.info("Thread 1 got through MONITOR_1 - doing stuff");
                LOGGER.info("Thread 1 state: {}, interrupted: {}", getState(), isInterrupted());
                pretendToDoStuff();
                LOGGER.info("Thread 1 done doing stuff");
                LOGGER.info("Thread 1 state: {}, interrupted: {}", getState(), isInterrupted());
                LOGGER.info("Thread 1 waiting for MONITOR_2");
                synchronized (MONITOR_2)
                {
                    LOGGER.info("Thread 1 got through MONITOR_2 - doing stuff");
                    pretendToDoStuff();
                }
            }
            LOGGER.info("DONE!");
        }

        private static void pretendToDoStuff()
        {
            try
            {
                sleep(2000);
            } catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
                LOGGER.error("Thread interrupted while pretending to do sth.", e);
                throw new RuntimeException("Thread interrupted while pretending to do sth.", e);
            }
        }

    }

    private class MyWorkerThread2 extends Thread
    {

        @Override
        public void run()
        {
            LOGGER.info("Thread 2 waiting for MONITOR_2");
            synchronized (MONITOR_2)
            {
                LOGGER.info("Thread 2 got through MONITOR_2 - doing stuff");
                LOGGER.info("Thread 2 state: {}, interrupted: {}", getState(), isInterrupted());
                pretendToDoStuff();
                LOGGER.info("Thread 2 done doing stuff");
                LOGGER.info("Thread 2 state: {}, interrupted: {}", getState(), isInterrupted());
                LOGGER.info("Thread 2 waiting for MONITOR_1");
                synchronized (MONITOR_1)
                {
                    LOGGER.info("Thread 2 got through MONITOR_1 - doing stuff");
                    pretendToDoStuff();
                }
            }
            LOGGER.info("DONE!");
        }

        private static void pretendToDoStuff()
        {
            try
            {
                sleep(2000);
            } catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
                LOGGER.error("Thread interrupted while pretending to do sth.", e);
                throw new RuntimeException("Thread interrupted while pretending to do sth.", e);
            }
        }

    }

    private static void sleepForAWhile()
    {
        try
        {
            Thread.sleep(3000);
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            LOGGER.info("Interrupted!", e);
            throw new RuntimeException("Interrupted!", e);
        }
    }
}