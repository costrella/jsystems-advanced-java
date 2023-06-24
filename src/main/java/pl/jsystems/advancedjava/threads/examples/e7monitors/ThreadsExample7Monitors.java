package pl.jsystems.advancedjava.threads.examples.e7monitors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

class ThreadsExample7Monitors
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsExample7Monitors.class);

    private static final SynchronizationMonitor MONITOR = new SynchronizationMonitor(0, null);

    public static void main(String[] args)
    {
        new ThreadsExample7Monitors().run();
    }

    private void run()
    {
        LOGGER.info("So many things happening at the same time...");

        Thread myFancyThread0 = new MyWorkerThread(MONITOR);
        myFancyThread0.start();
        LOGGER.info("Thread 0 started.");
        Thread myFancyThread1 = new MyWorkerThread(MONITOR);
        myFancyThread1.start();
        LOGGER.info("Thread 1 started.");
        Thread myFancyThread2 = new MyWorkerThread(MONITOR);
        myFancyThread2.start();
        LOGGER.info("Thread 2 started.");

        Scanner scanner = new Scanner(System.in);
        String input = null;
        while (!"q".equals(input))
        {
            input = scanner.nextLine();

            if ("ONE".equals(input) || input.isBlank())
            {
                LOGGER.info("I got something to do for one thread.");
                boolean hasListeningThreadBeenNotified = false;
                while (!hasListeningThreadBeenNotified)
                {
                    hasListeningThreadBeenNotified = tryNotifyThread(input);
                    if (!hasListeningThreadBeenNotified)
                    {
                        LOGGER.info("STARTING TO WAIT FOR A FREE THREAD");
                        waitForAvailableThread();
                        LOGGER.info("DONE WAITING FOR AVAILABLE THREADS");
                    }
                }
            }
            if ("ALL".equals(input))
            {
                LOGGER.info("I got something to do for all threads.");
                synchronized (MONITOR)
                {
                    if (MONITOR.getAvailableThreadsCounter() == 0)
                    {
                        LOGGER.info("NO WORKERS AVAILABLE, PLEASE TRY AGAIN LATER");
                    }
                    LOGGER.info("NOTIFYING SOMEONE");
                    MONITOR.setThreadInput(input);
                    MONITOR.notifyAll();
                }
            }
        }
        LOGGER.info("Left the input reader (scanner) part.");
        // what would happen if you comment the line(s) below?
        myFancyThread0.interrupt();
        myFancyThread1.interrupt();
        myFancyThread2.interrupt();
    }

    private boolean tryNotifyThread(final String input)
    {
        synchronized (MONITOR)
        {
            if (MONITOR.getAvailableThreadsCounter() != 0)
            {
                LOGGER.info("NOTIFYING SOMEONE ABOUT NEW INPUT: {}", input);
                MONITOR.setThreadInput(input);
                MONITOR.notify();
                return true;
            }
            LOGGER.info("NO THREADS AVAILABLE, LETS WAIT AND SEE");
        }
        return false;
    }

    private static void waitForAvailableThread()
    {
        try
        {
            Thread.sleep(5000);
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            LOGGER.info("Interrupted while waiting for available thread", e);
            throw new RuntimeException("Interrupted while waiting for available thread", e);
        }
    }
}
