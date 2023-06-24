package pl.jsystems.advancedjava.threads.examples.e8monitors;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

class NewUserInputNotifier
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsExample8Monitors.class);

    private final List<String> inputsToBeProcessed = new ArrayList<>();

    synchronized String waitForNewInput()
    {
        if (inputsToBeProcessed.size() > 0) {
            return inputsToBeProcessed.remove(0);
        }

        while (true)
        {
            LOGGER.info("Waiting for a new job!");
            waitPeacefully();
            if (!inputsToBeProcessed.isEmpty() && !Strings.isBlank(inputsToBeProcessed.get(0)))
            {
                LOGGER.info("I GOT NOTIFIED! NEW INPUT: {}", inputsToBeProcessed.get(0));
                return inputsToBeProcessed.remove(0);
            }
            LOGGER.info("NOTHING TO DO HERE, getting out of here and reapplying for job.");
        }
    }

    synchronized void notifyAboutNewInput(String threadInput)
    {
        LOGGER.info("NOTIFYING SOMEONE ABOUT NEW INPUT: {}", threadInput);
        inputsToBeProcessed.add(threadInput);
        notifyAll();
    }

    private void waitPeacefully()
    {
        try
        {
            wait();
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            LOGGER.error("Interrupted while waiting for new job.", e);
            throw new RuntimeException("Interrupted while waiting for new job.", e);
        }
    }
}
