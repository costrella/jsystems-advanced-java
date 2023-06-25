package pl.jsystems.advancedjava.threads.examples.e18countdownlatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.random.RandomGenerator;

class ThreadsExample18CountDownLatch
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsExample18CountDownLatch.class);

    private final CountDownLatch countDownLatch = new CountDownLatch(5);

    public static void main(String[] args)
    {
        new ThreadsExample18CountDownLatch().run();
    }

    private void run()
    {
        LOGGER.info("CountDownLatch");
        Runnable runnable = () ->
        {
            Integer delay = RandomGenerator.getDefault().nextInt(500, 2500);
            LOGGER.info("Doing some work! ETA {}", delay);
            sleepFor(delay);
            countDownLatch.countDown();
            LOGGER.info("Job done after {}", delay);
        };
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();

        LOGGER.info("Waiting for threads to do their jobs!");
        awaitForLatch();
        LOGGER.info("DONE!");
    }

    private void sleepFor(final Integer delay)
    {
        try
        {
            Thread.sleep(delay);
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            LOGGER.error("Interrupted!", e);
            throw new RuntimeException("Interrupted!", e);
        }
    }

    private void awaitForLatch()
    {
        try
        {
            countDownLatch.await();
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            LOGGER.info("Interrupted!", e);
            throw new RuntimeException("Interrupted!", e);
        }
    }
}
