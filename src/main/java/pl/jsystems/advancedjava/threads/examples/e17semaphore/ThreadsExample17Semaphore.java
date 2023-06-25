package pl.jsystems.advancedjava.threads.examples.e17semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

class ThreadsExample17Semaphore
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsExample17Semaphore.class);

    // alternative PriorityBlockingQueue

    public static void main(String[] args)
    {
        new ThreadsExample17Semaphore().run();
    }

    private void run()
    {
        LOGGER.info("Semaphore");
        ConnectionPool connectionPool = new ConnectionPool(5);
        new MyWorkerThread(connectionPool, 1).start();
        new MyWorkerThread(connectionPool, 2).start();
        new MyWorkerThread(connectionPool, 3).start();
    }

    static class ConnectionPool
    {
        private final List<Object> connectionPool = new ArrayList<>();
        private final Semaphore semaphore;


        ConnectionPool(int poolSize)
        {
            this.semaphore = new Semaphore(poolSize);
            IntStream.range(0, poolSize).forEach(ignored -> connectionPool.add(new Object()));
            LOGGER.info("Pool size with size {} created.", connectionPool.size());
        }

        List<Object> getConnection(int priority)
        {
            acquirePermit(priority);
            // this may fail - more about it soon! - we should wrap this with synchronized block!
            List<Object> result = new ArrayList<>();
            IntStream.range(0, priority).forEach(ignored -> result.add(connectionPool.remove(0)));
            LOGGER.info("Connection pool size: {}", connectionPool.size());
            return result;
        }

        void returnConnection(List<Object> objects, int priority)
        {
            // this may fail - more about it soon! - we should wrap this with synchronized block!
            connectionPool.addAll(objects);
            semaphore.release(priority);
        }

        private void acquirePermit(int priority)
        {
            try
            {
                semaphore.acquire(priority);
            } catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
                LOGGER.error("Interrupted!", e);
                throw new RuntimeException("Interrupted!", e);
            }
        }
    }
}
