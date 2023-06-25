package pl.jsystems.advancedjava.threads.examples.e14atomicoperations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class ThreadsExample14AtomicOperations
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsExample14AtomicOperations.class);

    public static void main(String[] args)
    {
        new ThreadsExample14AtomicOperations().run();
        new ThreadsExample14AtomicOperations().runAtomic();
    }

    private Integer counter = 0;
    private final AtomicInteger atomicCounter = new AtomicInteger(0);

    private void run()
    {
        Set<Thread> threadSet = IntStream.range(0, 100).mapToObj(threadNumber -> new Thread(() ->
        {
            LOGGER.info("Thread {} - counter value: {}", threadNumber, counter);
            counter++;
            LOGGER.info("Thread {} - counter value: {}", threadNumber, counter);
        })).collect(Collectors.toSet());

        threadSet.forEach(Thread::start);
        threadSet.forEach(this::joinThreadConsumer);
        LOGGER.info("At the end counter value is: {}", counter);
    }

    private void runAtomic()
    {
        Set<Thread> threadSet = IntStream.range(0, 100).mapToObj(threadNumber -> new Thread(() ->
        {
            LOGGER.info("Thread {} - atomic counter value: {}", threadNumber, atomicCounter.get());
            atomicCounter.incrementAndGet();
            LOGGER.info("Thread {} - atomic counter value: {}", threadNumber, atomicCounter.get());
        })).collect(Collectors.toSet());

        threadSet.forEach(Thread::start);
        threadSet.forEach(this::joinThreadConsumer);
        LOGGER.info("At the end atomic counter value is: {}", atomicCounter);
    }

    private void joinThreadConsumer(Thread thread)
    {
        try
        {
            thread.join();
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            LOGGER.error("Interrupted!", e);
            throw new RuntimeException("Interrupted!", e);
        }
    }
}
