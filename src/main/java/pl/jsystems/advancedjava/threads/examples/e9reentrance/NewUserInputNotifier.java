package pl.jsystems.advancedjava.threads.examples.e9reentrance;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

class NewUserInputNotifier
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsExample9Reentrance.class);

    private final List<Character> inputsToBeProcessed = new ArrayList<>();

    synchronized Character waitForNewInput()
    {
        if (inputsToBeProcessed.size() > 0)
        {
            return inputsToBeProcessed.remove(0);
        }

        while (true)
        {
            LOGGER.info("Waiting for a new job!");
            waitPeacefully();
            if (!inputsToBeProcessed.isEmpty() && !Strings.isBlank(String.valueOf(inputsToBeProcessed.get(0))))
            {
                LOGGER.info("I GOT NOTIFIED! NEW INPUT: {}", inputsToBeProcessed.get(0));
                return inputsToBeProcessed.remove(0);
            }
            LOGGER.info("NOTHING TO DO HERE, getting out of here and reapplying for job.");
        }
    }

    void processNewLetter(char input)
    {
        LOGGER.info("NOTIFYING SOMEONE ABOUT NEW INPUT: {}", input);
        synchronized (this)
        {
            inputsToBeProcessed.add(input);
            notifyAll();
        }
    }

    void processLetters(String input)
    {
        LOGGER.info("Multiple inputs provided, adding one by one!: {}", input);
        synchronized (this)
        {
            for (Character character : input.toCharArray())
            {
                processNewLetter(character);
            }
        }
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