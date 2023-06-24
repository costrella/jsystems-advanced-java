package pl.jsystems.advancedjava.threads.examples.e2joiningthreads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class ThreadsExample2JoiningThreads
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsExample2JoiningThreads.class);

    public static void main(String[] args)
    {
        new ThreadsExample2JoiningThreads().run();
    }

    private void run()
    {
        LOGGER.info("So many things happening at the same time...");

        Thread myNewThread = new Thread(() -> LOGGER.info("Doing stuff in other thread!"));
        myNewThread.start();
        joinWith(myNewThread);
        LOGGER.info("That's all for the new thread folks!");

        Thread myFancyThread = new MyFancyThread();
        myFancyThread.start();
        joinWith(myFancyThread);
        LOGGER.info("That's all for the my fancy thread folks!");

        LOGGER.info("Now - loops!");
        List<Thread> threads = new ArrayList<>();
        IntStream.range(0, 10).forEach(i -> threads.add(new Thread(() -> LOGGER.info("Doing stuff in other thread!"))));
        IntStream.range(0, 10).forEach(i -> threads.add(new MyFancyThread()));
        IntStream.range(0, 10).forEach(i -> threads.add(new MyThread(() -> LOGGER.info("Doing stuff in my thread!"))));
        threads.forEach(Thread::start);
        threads.forEach(this::joinWith);
        LOGGER.info("Now - end of loop!");
    }

    private void joinWith(Thread thread)
    {
        try
        {
            thread.join();
        } catch (InterruptedException e)
        {
            LOGGER.error("Error while waiting for thread to end.", e);
            Thread.currentThread().interrupt();
            throw new RuntimeException("Error while waiting for thread to end.", e);
        }
    }

    private static class MyFancyThread extends Thread
    {

        @Override
        public void run()
        {
            LOGGER.info("Doing stuff in my fancy thread!");
        }
    }

    private static class MyThread extends Thread
    {
        private final Runnable runnable;

        private MyThread(Runnable runnable)
        {
            this.runnable = runnable;
        }

        @Override
        public void run()
        {
            LOGGER.info("Doing something + what has been asked of me: ");
            runnable.run();
        }
    }
}
