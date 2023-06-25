package pl.jsystems.advancedjava.threads.examples.e16morethreadpools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.random.RandomGenerator;
import java.util.stream.IntStream;

class ThreadsExample16MoreThreadPools
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsExample16MoreThreadPools.class);

    public static void main(String[] args)
    {
        new ThreadsExample16MoreThreadPools().run();
    }

    private final AtomicInteger counter = new AtomicInteger(0);

    private void run()
    {
        // executor with single thread - perfect for background tasks
        ExecutorService executors = Executors.newSingleThreadExecutor();
        executors.shutdown();

        // new cached thread pool - pool will grow bigger when needed and become smaller if no demand.
        executors = Executors.newCachedThreadPool();
        executors.shutdown();

        // this will also create a cached thread pool - this time we start with 10 threads, we go to max 20,
        // and the unused threads will live for about 2mins.
        executors = new ThreadPoolExecutor(10, 20,
            120L, TimeUnit.SECONDS,
            new SynchronousQueue<>());
        executors.shutdown();

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        scheduledExecutorService.schedule(() -> LOGGER.info("This will happen in 5sec"), 5, TimeUnit.SECONDS);
        LOGGER.info("We've planned something for the future!");

        scheduledExecutorService.scheduleAtFixedRate(() -> {
            sleepFor(4000);
            LOGGER.info("At Fixed rate!");
        }, 5L, 5L, TimeUnit.SECONDS);

        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            sleepFor(4000);
            LOGGER.info("With delay!");
        }, 5L, 5L, TimeUnit.SECONDS);

        sleepFor(30000);
        scheduledExecutorService.shutdown();
    }

    private static void sleepFor(int howLong)
    {
        try
        {
            Thread.sleep(howLong);
        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }

    private void waitForFuture(final Future<?> future)
    {
        try
        {
            future.get();
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            LOGGER.error("Interrupted", e);
            throw new RuntimeException("Interrupted", e);
        } catch (ExecutionException e)
        {
            LOGGER.error("Something went wrong during execution!");
            throw new RuntimeException(e);
        }
    }
}
