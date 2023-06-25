package pl.jsystems.advancedjava.threads.examples.e19parallelstreams;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.IntStream;

class ThreadsExample19ParallelStreams
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsExample19ParallelStreams.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException
    {
        new ThreadsExample19ParallelStreams().run();
    }

    private void run() throws ExecutionException, InterruptedException
    {
        long start = Instant.now().toEpochMilli();

        // this fork join pool is added to handle parallel stream. By default, system fork join pool is used,
        // and it is used for other system related tasks - if we drain it - it may halt the app.
        ForkJoinTask<List<Long>> task = new ForkJoinPool(10).submit(() ->
                IntStream.range(0, 300000).parallel().mapToObj(Long::valueOf).map(this::sumAllNumbersLowerThan).toList());
        List<Long> sums = task.get();
        long end = Instant.now().toEpochMilli();

        LOGGER.info("Biggest sum {} - after(parallel) {}ms", sums.stream().max(Long::compare), end - start);

        start = Instant.now().toEpochMilli();
        List<Long> otherSums = IntStream.range(0, 300000).mapToObj(Long::valueOf).map(this::sumAllNumbersLowerThan).toList();
        end = Instant.now().toEpochMilli();

        LOGGER.info("Biggest sum {} - after(sequential) {}ms", otherSums.stream().max(Long::compare), end - start);
    }

    // naive implementation
    private Long sumAllNumbersLowerThan(Long number)
    {
        long result = 0L;
        for (long i = 0; i < number; i++)
        {
            result = result + i;
        }
        return result;
    }
}
