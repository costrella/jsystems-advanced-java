package pl.jsystems.advancedjava.threads.examples.e4monitors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

class ThreadsExample4Monitors
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsExample4Monitors.class);

    private static final Object MY_THREADS_SYNCHRONIZATION_MONITOR = new Object();

    public static void main(String[] args)
    {
        new ThreadsExample4Monitors().run();
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
            LOGGER.info("I got something to do for someone.");
            synchronized (MY_THREADS_SYNCHRONIZATION_MONITOR)
            {
                LOGGER.info("NOTIFYING SOMEONE");
                MY_THREADS_SYNCHRONIZATION_MONITOR.notify();
            }
        }

        LOGGER.info("Lets see how notify all works now.");
        Thread myFancyThread3 = new MyWorkerThread();
        LOGGER.info("Created new thread 3.");
        myFancyThread3.start();
        LOGGER.info("Thread 3 started.");
        Thread myFancyThread4 = new MyWorkerThread();
        LOGGER.info("Created new thread 4.");
        myFancyThread4.start();
        LOGGER.info("Thread 4 started.");
        Thread myFancyThread5 = new MyWorkerThread();
        LOGGER.info("Created new thread 5.");
        myFancyThread5.start();
        LOGGER.info("Thread 5 started.");

        input = null;
        while (!"q".equals(input))
        {
            input = scanner.nextLine();
            LOGGER.info("I got something to do for someone.");
            synchronized (MY_THREADS_SYNCHRONIZATION_MONITOR)
            {
                LOGGER.info("NOTIFYING ALL WAITING");
                MY_THREADS_SYNCHRONIZATION_MONITOR.notifyAll();
            }
        }
    }


    private static class MyWorkerThread extends Thread
    {

        @Override
        public void run()
        {
            synchronized (MY_THREADS_SYNCHRONIZATION_MONITOR)
            {
                LOGGER.info("Waiting for a new job!");
                waitingForSomeoneToNotifyMe();
                LOGGER.info("I GOT NOTIFIED!");
            }

            for (int i = 0; i < 20; i++)
            {
                LOGGER.info("Doing stuff in my fancy thread! - loop {}", i);
                pretendToDoStuff();
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
                LOGGER.error("Thread interrupted while waiting for a job.", e);
                throw new RuntimeException("Thread interrupted while waiting for a job.", e);
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
                LOGGER.error("Thread interrupted while pretending to do sth.", e);
                throw new RuntimeException("Thread interrupted while pretending to do sth.", e);
            }
        }
    }
}
