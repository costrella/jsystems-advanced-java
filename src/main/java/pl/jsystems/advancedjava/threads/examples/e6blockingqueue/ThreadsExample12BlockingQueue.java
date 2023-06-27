package pl.jsystems.advancedjava.threads.examples.e6blockingqueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class ThreadsExample12BlockingQueue
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsExample12BlockingQueue.class);

    // alternative PriorityBlockingQueue
    private final BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(2);

    public static void main(String[] args)
    {
        new ThreadsExample12BlockingQueue().run();
    }

    private void run()
    {
        LOGGER.info("So many things happening at the same time...");

        Thread myFancyThread0 = new MyWorkerThread(blockingQueue);
        myFancyThread0.start();
        LOGGER.info("Thread 0 started.");
        Thread myFancyThread1 = new MyWorkerThread(blockingQueue);
        myFancyThread1.start();
        LOGGER.info("Thread 1 started.");
        Thread myFancyThread2 = new MyWorkerThread(blockingQueue);
        myFancyThread2.start();
        LOGGER.info("Thread 2 started.");

        Scanner scanner = new Scanner(System.in);
        String input = null;
        while (!"q".equals(input))
        {
            input = scanner.nextLine();
            LOGGER.info("NEW INPUT: {}", input);
            if ("ALL".equals(input))
            {
                LOGGER.info("I got something to do for all threads.");
                // alternative method offer(...) - returns true if added or false if no space
                // alternative method offer(..., timeToWait) - waits for space, returns true if added or false if no space
                // alternative method add(...) - returns ture if added, exception if no space
                putIntoQueue(input);
            }
            else if (!input.isBlank())
            {
                LOGGER.info("I got something to do for one thread: {}", input);
                putIntoQueue(input);
            }
        }
        LOGGER.info("Left the input reader (scanner) part.");
        // what would happen if you comment the line(s) below?
        myFancyThread0.interrupt();
        myFancyThread1.interrupt();
        myFancyThread2.interrupt();
    }

    private void putIntoQueue(final String input)
    {
        try
        {
            blockingQueue.put(input);
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            LOGGER.error("Interrupted while waiting to add sth to queue!", e);
            throw new RuntimeException(e);
        }
    }
}
