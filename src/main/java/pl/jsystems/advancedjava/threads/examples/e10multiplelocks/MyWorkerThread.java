package pl.jsystems.advancedjava.threads.examples.e10multiplelocks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class MyWorkerThread extends Thread
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MyWorkerThread.class);

    private final NewUserInputNotifier monitor;

    MyWorkerThread(NewUserInputNotifier monitor)
    {
        this.monitor = monitor;
    }

    @Override
    public void run()
    {
        while (true)
        {
            Character input = monitor.waitForNewInput();
            pretendToDoStuff(input);
            LOGGER.info("Done with my job - I'm available again!");
        }
    }

    private static void pretendToDoStuff(Character input)
    {
        try
        {
            for (int i = 0; i < 5; i++)
            {
                LOGGER.info("Doing stuff in my fancy thread for input {}! - loop {}", input, i);
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
