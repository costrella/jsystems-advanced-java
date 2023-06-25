package pl.jsystems.advancedjava.threads.examples.e10multiplelocks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

class ThreadsExample10MultipleLocks
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsExample10MultipleLocks.class);

    private final NewUserInputNotifier userInputNotifier = new NewUserInputNotifier(new Object());

    public static void main(String[] args)
    {
        new ThreadsExample10MultipleLocks().run();
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
            if (!input.isBlank() && input.length() == 1)
            {
                LOGGER.info("I got something to do for one thread: {}", input);
                userInputNotifier.processNewLetter(input.charAt(0));
            }
            else
            {
                LOGGER.info("I got something to do for all threads.");
                userInputNotifier.processLetters(input);
            }
        }
        LOGGER.info("Left the input reader (scanner) part.");
        // what would happen if you comment the line(s) below?
        myFancyThread0.interrupt();
        myFancyThread1.interrupt();
        myFancyThread2.interrupt();
    }
}
