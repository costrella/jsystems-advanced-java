package pl.jsystems.advancedjava.lambdas.solutions.s12inlinelambda;

@FunctionalInterface
public interface TriPredicate<T>
{
    boolean test(T value1, T value2, T value3);
}
