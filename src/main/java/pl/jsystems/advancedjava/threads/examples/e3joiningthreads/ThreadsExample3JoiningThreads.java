package pl.jsystems.advancedjava.threads.examples.e3joiningthreads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class ThreadsExample3JoiningThreads
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsExample3JoiningThreads.class);

    public static void main(String[] args)
    {
        new ThreadsExample3JoiningThreads().run();
    }

    private void run()
    {
        LOGGER.info("So many things happening at the same time...");

        Thread myNewThread = new Thread(() -> LOGGER.info("Doing stuff in other thread!"));
        myNewThread.start();
        joinWith(myNewThread);
        LOGGER.info("That's all for the new thread folks!");

        Thread myFancyThread = new MyFancyThread();
        myFancyThread.start();
        LOGGER.info("Waiting for my fancy thread to finish");
        joinWith(myFancyThread);
        LOGGER.info("That's all for the my fancy thread folks!");

        LOGGER.info("Now - loops!");
        List<Thread> threads = new ArrayList<>();
        IntStream.range(0, 10).forEach(i -> threads.add(new Thread(() -> LOGGER.info("Doing stuff in some thread!"))));
        IntStream.range(0, 10).forEach(i -> threads.add(new MyFancyThread()));
        LOGGER.info("Starting all threads");
        threads.forEach(Thread::start);
        LOGGER.info("Started all threads");
        threads.forEach(this::joinWith);
        LOGGER.info("Now - end of loop!");
    }

    private void joinWith(Thread thread)
    {
        try
        {
            thread.join();
        } catch (InterruptedException e)
        {
            LOGGER.error("Error while waiting for thread to end.", e);
            Thread.currentThread().interrupt();
            throw new RuntimeException("Error while waiting for thread to end.", e);
        }
    }

    private static class MyFancyThread extends Thread
    {
        @Override
        public void run()
        {
            LOGGER.info("Doing stuff in my fancy thread! (GOING TO SLEEP)");
            sleepABit();
            LOGGER.info("Doing stuff in my fancy thread! (WAKING UP)");
        }
    }

    private static void sleepABit()
    {
        try
        {
            Thread.sleep(1000);
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            LOGGER.error("Interrupted!");
            throw new RuntimeException(e);
        }
    }
}
