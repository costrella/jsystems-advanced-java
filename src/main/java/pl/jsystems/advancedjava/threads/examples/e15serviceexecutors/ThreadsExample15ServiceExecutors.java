package pl.jsystems.advancedjava.threads.examples.e15serviceexecutors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.random.RandomGenerator;
import java.util.stream.IntStream;

class ThreadsExample15ServiceExecutors
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsExample15ServiceExecutors.class);

    public static void main(String[] args)
    {
        new ThreadsExample15ServiceExecutors().run();
    }

    private final AtomicInteger counter = new AtomicInteger(0);

    private void run()
    {
        ExecutorService executors = Executors.newFixedThreadPool(10);
        executors.submit(() -> LOGGER.info("Thread from pool is called!"));

        Future<Integer> futureResult = executors.submit(() ->
        {
            Thread.sleep(2000);
            return RandomGenerator.getDefault().nextInt(10);
        });

        LOGGER.info("Waiting for the future result....");
        waitForFuture(futureResult);
        LOGGER.info("Got it, at last! {}", futureResult);

        var noResultFutures = IntStream.range(0, 100).mapToObj(threadNumber -> executors.submit(() ->
                LOGGER.info("Thread {} - counter value: {}", threadNumber, counter.incrementAndGet()))).toList();

        noResultFutures.forEach(this::waitForFuture);
        LOGGER.info("At the end counter value is: {}", counter);

        List<Future<Integer>> futuresWithResults = IntStream.range(0, 100).mapToObj(threadNumber -> executors.submit(() ->
        {
            LOGGER.info("Thread {} - counter value: {}", threadNumber, counter.incrementAndGet());
            return threadNumber;
        })).toList();

        futuresWithResults.forEach(this::waitForFuture);
        LOGGER.info("At the end counter value is: {}", counter);
        // or await termination
        executors.shutdown();
        LOGGER.info("That's all folks!");
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
