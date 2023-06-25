package pl.jsystems.advancedjava.threads.examples.e13synchronizingstate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class ThreadsExample13SynchronizingState
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsExample13SynchronizingState.class);


    public static void main(String[] args)
    {
        new ThreadsExample13SynchronizingState().run();
    }

    private Integer counter = 0;
    private Integer synchronizedCounter = 0;

    private void run()
    {
        Set<Thread> threads = IntStream.range(0, 100).mapToObj(i -> new Thread(() ->
        {
            LOGGER.info("Thread {} - counter value: {}", i, counter);
            counter++;
            LOGGER.info("Thread {} - counter value: {}", i, counter);
        }
        )).collect(Collectors.toSet());

        threads.forEach(Thread::start);
        threads.forEach(this::joinWithThread);

        Set<Thread> synchronizedThreads = IntStream.range(0, 100).mapToObj(i -> new Thread(() ->
        {
            LOGGER.info("Synchronized thread {} - counter value: {}", i, counter);
            // we can also do synchronized block here!
            updateValue();
            LOGGER.info("Synchronized thread {} - counter value: {}", i, counter);
        }
        )).collect(Collectors.toSet());

        synchronizedThreads.forEach(Thread::start);
        synchronizedThreads.forEach(this::joinWithThread);

        LOGGER.info("Counter value at the end: {}", counter);
        LOGGER.info("Synchronized counter value at the end: {}", synchronizedCounter);
    }

    private synchronized void updateValue()
    {
        synchronizedCounter++;
    }

    private void joinWithThread(final Thread t)
    {
        try
        {
            t.join();
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            LOGGER.error("Interrupted!", e);
            throw new RuntimeException("Interrupted!", e);
        }
    }
}
