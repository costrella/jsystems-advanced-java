package pl.jsystems.advancedjava.threads.examples.e2sleepandinterruption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ThreadsExample2SleepAndInterruption
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsExample2SleepAndInterruption.class);

    public static void main(String[] args)
    {
        new ThreadsExample2SleepAndInterruption().run();
    }

    private void run()
    {
        LOGGER.info("So many things happening at the same time...");

        Thread sleepyThread = new SleepyThread();
        sleepyThread.start();
        LOGGER.info("Taking a short nap in the main thread.");
        takeAShortNap();
        LOGGER.info("Main thread is well rested now - time to make some noise!");
        sleepyThread.interrupt();
        LOGGER.info("Noise has been made.");
        LOGGER.info("Have we interrupted someone?! Sleepy thread is alive: {}, is interrupted: {}", sleepyThread.isAlive(), sleepyThread.isInterrupted());
        joinWith(sleepyThread);
        LOGGER.info("Yes we have! Sleepy thread is alive: {}, is interrupted: {}", sleepyThread.isAlive(), sleepyThread.isInterrupted());
    }

    private void joinWith(Thread thread)
    {
        try
        {
            thread.join();
        } catch (InterruptedException e)
        {
            LOGGER.error("Someone interrupted main thread!", e);
            Thread.currentThread().interrupt();
            throw new RuntimeException("Someone interrupted main thread!", e);
        }
    }

    private static void takeAShortNap()
    {
        try
        {
            Thread.sleep(2000);
        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }

    private static class SleepyThread extends Thread
    {

        @Override
        public void run()
        {
            try
            {
                LOGGER.info("Sleepy thread going to do its job - going to sleep! T");
                sleep(10000);
            } catch (InterruptedException e)
            {
                LOGGER.info("Sleepy thread got interrupted! Waiting up, and doing... something...");
                LOGGER.error("We probably want to throw another exception, and mark this thread as interrupted.", e);
                Thread.currentThread().interrupt();
                throw new RuntimeException("Sleepy thread got interrupted!", e);
            }
        }
    }
}
