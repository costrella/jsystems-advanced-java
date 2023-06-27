package pl.jsystems.advancedjava.lambdas.exercises.e12inlinelambda;

@FunctionalInterface
public interface TriPredicate<T>
{
    boolean test(T value1, T value2, T value3);
}
