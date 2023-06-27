package pl.jsystems.advancedjava.lambdas.solutions.s11functionalinterface;

@FunctionalInterface
public interface TriPredicate<T>
{
    boolean test(T value1, T value2, T value3);
}
