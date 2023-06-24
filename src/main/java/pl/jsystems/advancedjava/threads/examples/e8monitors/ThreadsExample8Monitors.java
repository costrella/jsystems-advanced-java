package pl.jsystems.advancedjava.threads.examples.e8monitors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

class ThreadsExample8Monitors
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsExample8Monitors.class);

    private final NewUserInputNotifier userInputNotifier = new NewUserInputNotifier();

    public static void main(String[] args)
    {
        new ThreadsExample8Monitors().run();
    }

    private void run()
    {
        LOGGER.info("So many things happening at the same time...");

        Thread myFancyThread0 = new MyWorkerThread(userInputNotifier);
        myFancyThread0.start();
        LOGGER.info("Thread 0 started.");
        Thread myFancyThread1 = new MyWorkerThread(userInputNotifier);
        myFancyThread1.start();
        LOGGER.info("Thread 1 started.");
        Thread myFancyThread2 = new MyWorkerThread(userInputNotifier);
        myFancyThread2.start();
        LOGGER.info("Thread 2 started.");

        Scanner scanner = new Scanner(System.in);
        String input = null;
        while (!"q".equals(input))
        {
            input = scanner.nextLine();
            LOGGER.info("NEW INPUT: {}", input);
            if ("ALL".equals(input))
            {
                LOGGER.info("I got something to do for all threads.");
                userInputNotifier.notifyAboutNewInput(input);
            }
            else if (!input.isBlank())
            {
                LOGGER.info("I got something to do for one thread: {}", input);
                userInputNotifier.notifyAboutNewInput(input);
            }
        }
        LOGGER.info("Left the input reader (scanner) part.");
        // what would happen if you comment the line(s) below?
        myFancyThread0.interrupt();
        myFancyThread1.interrupt();
        myFancyThread2.interrupt();
    }
}
