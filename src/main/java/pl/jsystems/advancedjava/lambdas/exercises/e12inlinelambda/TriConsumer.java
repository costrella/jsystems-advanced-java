package pl.jsystems.advancedjava.lambdas.exercises.e12inlinelambda;

@FunctionalInterface
public interface TriConsumer<T>
{
    void consume(T value1, T value2, T value3);
}
