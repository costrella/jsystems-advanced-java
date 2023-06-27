package pl.jsystems.advancedjava.threads.examples.e5monitors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ThreadsExample5Monitors
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsExample5Monitors.class);

    public static void main(String[] args)
    {
        new ThreadsExample5Monitors().run();
    }

    private void run()
    {
        LOGGER.info("So many things happening at the same time...");
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Condition condition2 = lock.newCondition();

        new MyWorkerThread(lock, condition).start();
        new MyWorkerThread(lock, condition).start();
        new MyWorkerThread(lock, condition2).start();
        new MyWorkerThread(lock, condition2).start();
        LOGGER.info("Threads started.");

        Scanner scanner = new Scanner(System.in);
        String input = null;

        while (!"q".equals(input))
        {
            input = scanner.nextLine();

            LOGGER.info("I got something to do for one thread.");
            lock.lock();
            try
            {
                LOGGER.info("NOTIFYING SOMEONE");
                if (input.startsWith("1"))
                {
                    condition.signal();
                }
                else
                {
                    condition2.signalAll();
                }
            } finally
            {
                lock.unlock();
            }
        }
    }


    private static class MyWorkerThread extends Thread
    {
        private final Lock lock;
        private final Condition condition;

        private MyWorkerThread(Lock lock, Condition condition)
        {
            this.lock = lock;
            this.condition = condition;
        }

        @Override
        public void run()
        {
            while (true)
            {

                LOGGER.info("Waiting for a new job!");
                waitingForSomeoneToNotifyMe();
                LOGGER.info("I GOT NOTIFIED!");

                for (int i = 0; i < 10; i++)
                {
                    LOGGER.info("Doing stuff in my fancy thread! - loop {}", i);
                    pretendToDoStuff();
                }
                LOGGER.info("Done with my job - I'm available again!");
            }
        }

        private void waitingForSomeoneToNotifyMe()
        {
            lock.lock();
            try
            {
                condition.await();
            } catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread interrupted while waiting for a job.");
            } finally
            {
                lock.unlock();
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
