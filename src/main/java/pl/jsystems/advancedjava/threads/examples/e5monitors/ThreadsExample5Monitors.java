package pl.jsystems.advancedjava.threads.examples.e5monitors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

class ThreadsExample5Monitors
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsExample5Monitors.class);

    private static final Object MY_THREADS_SYNCHRONIZATION_MONITOR = new Object();

    public static void main(String[] args)
    {
        new ThreadsExample5Monitors().run();
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

            if ("ONE".equals(input))
            {
                LOGGER.info("I got something to do for one thread.");
                synchronized (MY_THREADS_SYNCHRONIZATION_MONITOR)
                {
                    LOGGER.info("NOTIFYING SOMEONE");
                    MY_THREADS_SYNCHRONIZATION_MONITOR.notify();
                }
            }
            if ("ALL".equals(input))
            {
                LOGGER.info("I got something to do for all threads.");
                synchronized (MY_THREADS_SYNCHRONIZATION_MONITOR)
                {
                    LOGGER.info("NOTIFYING SOMEONE");
                    MY_THREADS_SYNCHRONIZATION_MONITOR.notifyAll();
                }
            }
        }
    }


    private static class MyWorkerThread extends Thread
    {

        @Override
        public void run()
        {
            while (true)
            {
                synchronized (MY_THREADS_SYNCHRONIZATION_MONITOR)
                {
                    LOGGER.info("Waiting for a new job!");
                    waitingForSomeoneToNotifyMe();
                    LOGGER.info("I GOT NOTIFIED!");
                }

                for (int i = 0; i < 10; i++)
                {
                    LOGGER.info("Doing stuff in my fancy thread! - loop {}", i);
                    pretendToDoStuff();
                }
                LOGGER.info("Done with my job - I'm available again!");
            }
        }

        private static void waitingForSomeoneToNotifyMe()
        {
            try
            {
                MY_THREADS_SYNCHRONIZATION_MONITOR.wait();
            } catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread interrupted while waiting for a job.");
            }
        }

        private static void pretendToDoStuff()
        {
            try
            {
                sleep(1000);
            } catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread interrupted while pretending to do sth.");
            }
        }
    }
}
