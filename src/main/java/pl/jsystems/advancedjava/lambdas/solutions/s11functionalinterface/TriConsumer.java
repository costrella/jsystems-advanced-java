package pl.jsystems.advancedjava.lambdas.solutions.s11functionalinterface;

@FunctionalInterface
public interface TriConsumer<T>
{
    void consume(T value1, T value2, T value3);
}
