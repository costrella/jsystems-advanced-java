package pl.jsystems.advancedjava.threads.examples.e1creatingthreads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class ThreadsExample1CreatingThreads
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsExample1CreatingThreads.class);

    public static void main(String[] args)
    {
        new ThreadsExample1CreatingThreads().run();
    }

    private void run()
    {
        LOGGER.info("So many things happening at the same time...");

        Thread myNewThread = new Thread(() -> LOGGER.info("Doing stuff in other thread!"));
        myNewThread.start();

        LOGGER.info("That's all for the new thread folks!");

        Thread myFancyThread = new MyFancyThread();
        myFancyThread.start();

        LOGGER.info("That's all for the my fancy thread folks!");

        LOGGER.info("Now - loops!");
        List<Thread> threads = new ArrayList<>();
        IntStream.range(0, 10).forEach(i -> threads.add(new Thread(() -> LOGGER.info("Doing stuff in other thread!"))));
        IntStream.range(0, 10).forEach(i -> threads.add(new MyFancyThread()));
        IntStream.range(0, 10).forEach(i -> threads.add(new MyThread(() -> LOGGER.info("Doing stuff in my thread!"))));
        threads.forEach(Thread::start);
        LOGGER.info("Now - end of loop!");
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
        private MyThread(Runnable runnable) {
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
