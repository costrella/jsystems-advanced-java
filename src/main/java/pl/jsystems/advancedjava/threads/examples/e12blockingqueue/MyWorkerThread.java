package pl.jsystems.advancedjava.threads.examples.e12blockingqueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;

class MyWorkerThread extends Thread
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MyWorkerThread.class);

    private final BlockingQueue<String> queueOfInputs;

    MyWorkerThread(BlockingQueue<String> queueOfInputs)
    {
        this.queueOfInputs = queueOfInputs;
    }

    @Override
    public void run()
    {
        while (true)
        {
            String input = waitForNewInput();
            pretendToDoStuff(input);
            LOGGER.info("Done with my job - I'm available again!");
        }
    }

    private String waitForNewInput()
    {
        try
        {
            return queueOfInputs.take();
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            LOGGER.error("Interrupted while waiting for new input!", e);
            throw new RuntimeException("Interrupted while waiting for new input!", e);
        }
    }

    private static void pretendToDoStuff(String input)
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
