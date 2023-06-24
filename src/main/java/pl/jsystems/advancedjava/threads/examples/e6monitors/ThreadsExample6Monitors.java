package pl.jsystems.advancedjava.threads.examples.e6monitors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

class ThreadsExample6Monitors
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsExample6Monitors.class);

    private final Object MY_THREADS_SYNCHRONIZATION_MONITOR = new Object();
    private String inputToProcess = null;
    private int threadsReadyToAcceptNewTasks = 0;

    public static void main(String[] args)
    {
        new ThreadsExample6Monitors().run();
    }

    private void run()
    {
        LOGGER.info("So many things happening at the same time...");

        Thread myFancyThread0 = new MyWorkerThread();
        LOGGER.info("Created new thread 0.");
        myFancyThread0.start();
        LOGGER.info("Thread 0 started.");
        Thread myFancyThread1 = new MyWorkerThread();
        LOGGER.info("Created new thread 1.");
        myFancyThread1.start();
        LOGGER.info("Thread 1 started.");
        Thread myFancyThread2 = new MyWorkerThread();
        LOGGER.info("Created new thread 2.");
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
                    if (!hasListeningThreadBeenNotified) {
                        LOGGER.info("STARTING TO WAIT FOR A FREE THREAD");
                        waitForAvailableThread();
                        LOGGER.info("DONE WAITING FOR AVAILABLE THREADS");
                    }
                }
            }
            if ("ALL".equals(input))
            {
                LOGGER.info("I got something to do for all threads.");
                synchronized (MY_THREADS_SYNCHRONIZATION_MONITOR)
                {
                    if (threadsReadyToAcceptNewTasks == 0)
                    {
                        LOGGER.info("NO WORKERS AVAILABLE, PLEASE TRY AGAIN LATER");
                    }
                    LOGGER.info("NOTIFYING SOMEONE");
                    inputToProcess = input;
                    MY_THREADS_SYNCHRONIZATION_MONITOR.notifyAll();
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
        synchronized (MY_THREADS_SYNCHRONIZATION_MONITOR)
        {
            if (threadsReadyToAcceptNewTasks != 0)
            {
                LOGGER.info("NOTIFYING SOMEONE ABOUT NEW INPUT: {}", input);
                inputToProcess = input;
                MY_THREADS_SYNCHRONIZATION_MONITOR.notify();
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


    private class MyWorkerThread extends Thread
    {

        @Override
        public void run()
        {
            String jobInput;
            while (true)
            {
                synchronized (MY_THREADS_SYNCHRONIZATION_MONITOR)
                {
                    LOGGER.info("Waiting for a new job!");
                    threadsReadyToAcceptNewTasks++;
                    waitingForSomeoneToNotifyMe();
                    jobInput = inputToProcess;
                    threadsReadyToAcceptNewTasks--;
                    if (jobInput == null || jobInput.isBlank())
                    {
                        LOGGER.info("NOTHING TO DO HERE, getting out of here and reapplying for job.");
                        // we could also do a while() loop inside this synch block, then we wouldn't leave the block,
                        // effect would be almost the same. We are now leaving the block for a bit, so chances are there will be noone waiting.
                        continue;
                    }
                    LOGGER.info("I GOT NOTIFIED! NEW INPUT: {}", inputToProcess);
                }

                for (int i = 0; i < 5; i++)
                {
                    LOGGER.info("Doing stuff in my fancy thread! - loop {}", i);
                    pretendToDoStuff();
                }
                LOGGER.info("Done with my job - I'm available again!");

            }
        }

        private void waitingForSomeoneToNotifyMe()
        {
            try
            {
                MY_THREADS_SYNCHRONIZATION_MONITOR.wait();
            } catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
                LOGGER.error("Thread interrupted while waiting for a job.", e);
                throw new RuntimeException("Thread interrupted while waiting for a job.", e);
            }
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
}
