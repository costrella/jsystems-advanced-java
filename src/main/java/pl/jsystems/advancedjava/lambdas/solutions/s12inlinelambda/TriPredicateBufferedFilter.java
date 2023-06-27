package pl.jsystems.advancedjava.lambdas.solutions.s12inlinelambda;


import java.util.ArrayList;
import java.util.List;

class TriPredicateBufferedFilter<T>
{
    private final List<Triplet<T>> triplets = new ArrayList<>();
    private final TriConsumer<? super T> consumer;
    private final TriPredicate<? super T> predicate;

    TriPredicateBufferedFilter(TriPredicate<? super T> predicate, TriConsumer<? super T> consumer)
    {
        this.predicate = predicate;
        this.consumer = consumer;
    }

    boolean bufferIfMatches(T value1, T value2, T value3)
    {
        if (predicate.test(value1, value2, value3))
        {
            return triplets.add(new Triplet<>(value1, value1, value3));
        }
        return false;
    }

    long consumeAllMatched()
    {
        for (Triplet<T> triplet : triplets)
        {
            consumer.consume(triplet.value1(), triplet.value2(), triplet.value3());
        }
        return triplets.size();
    }
}

record Triplet<T>(T value1, T value2, T value3)
{
};

