package pl.jsystems.advancedjava.threads.examples.e17semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

class MyWorkerThread extends Thread
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MyWorkerThread.class);

    private final ThreadsExample17Semaphore.ConnectionPool connectionPool;
    private final int priority;

    MyWorkerThread(ThreadsExample17Semaphore.ConnectionPool connectionPool, int priority)
    {
        this.connectionPool = connectionPool;
        this.priority = priority;
    }

    @Override
    public void run()
    {
        while (true)
        {
            LOGGER.info("THREAD P{} - waiting for job", priority);
            List<Object> connections = connectionPool.getConnection(priority);
            try
            {
                pretendToDoStuff(connections);
                LOGGER.info("THREAD P{} - Done with my job - I'm available again!", priority);
            } finally
            {
                connectionPool.returnConnection(connections, priority);
            }
        }
    }

    private void pretendToDoStuff(List<Object> connections)
    {
        try
        {
            for (int i = 0; i < 5; i++)
            {
                LOGGER.info("THREAD P{} - Doing stuff in my fancy thread! - loop {}", priority, i);
                sleep(10);
            }
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            LOGGER.error("Thread interrupted while pretending to do sth.", e);
            throw new RuntimeException("Thread interrupted while pretending to do sth.", e);
        }
    }
}
