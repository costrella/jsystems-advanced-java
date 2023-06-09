package pl.jsystems.advancedjava.generics.examples.e5genericmethods;

class Pair<VALUE1, VALUE2>
{
    private final VALUE1 firstValue;
    private final VALUE2 secondValue;

    private Pair(VALUE1 firstValue, VALUE2 secondValue)
    {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    VALUE1 getFirstValue()
    {
        return firstValue;
    }

    VALUE2 getSecondValue()
    {
        return secondValue;
    }

    @Override
    public String toString()
    {
        return "Pair{" +
                "firstValue=" + firstValue +
                ", secondValue=" + secondValue +
                '}';
    }

    static <T, R> Pair<T, R> createPair(T value1, R value2)
    {
        validate(value1, "First");
        validate(value2, "Second");
        return new Pair<>(value1, value2);
    }

    private static void validate(Object value, String paramName)
    {
        if (value == null)
        {
            throw new IllegalArgumentException(paramName + " value cannot be null.");
        }
    }
}
