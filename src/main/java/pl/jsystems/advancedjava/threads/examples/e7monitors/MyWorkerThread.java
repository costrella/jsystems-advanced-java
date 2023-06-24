package pl.jsystems.advancedjava.threads.examples.e7monitors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class MyWorkerThread extends Thread
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MyWorkerThread.class);

    private final SynchronizationMonitor monitor;

    MyWorkerThread(SynchronizationMonitor monitor)
    {
        this.monitor = monitor;
    }

    @Override
    public void run()
    {
        String jobInput;
        while (true)
        {
            synchronized (monitor)
            {
                while (true)
                {
                    LOGGER.info("Waiting for a new job!");
                    monitor.incrementAvailableThreadsCounter();
                    waitingForSomeoneToNotifyMe();
                    jobInput = monitor.getThreadInput();
                    monitor.decrementAvailableThreadsCounter();
                    if (jobInput != null && !jobInput.isBlank())
                    {
                        LOGGER.info("I GOT NOTIFIED! NEW INPUT: {}", jobInput);
                        break;
                    }
                    LOGGER.info("NOTHING TO DO HERE, getting out of here and reapplying for job.");
                }
            }

            pretendToDoStuff();
            LOGGER.info("Done with my job - I'm available again!");
        }
    }

    private void waitingForSomeoneToNotifyMe()
    {
        try
        {
            monitor.wait();
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
            for (int i = 0; i < 5; i++)
            {
                LOGGER.info("Doing stuff in my fancy thread! - loop {}", i);
                sleep(2000);
            }
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            LOGGER.error("Thread interrupted while pretending to do sth.", e);
            throw new RuntimeException("Thread interrupted while pretending to do sth.", e);
        }
    }
}
