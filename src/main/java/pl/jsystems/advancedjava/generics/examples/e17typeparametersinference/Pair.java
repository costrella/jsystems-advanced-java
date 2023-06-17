package pl.jsystems.advancedjava.generics.examples.e17typeparametersinference;

record Pair<VALUE1 extends Number, VALUE2 extends VALUE1>(VALUE1 firstValue, VALUE2 secondValue)
{

    @Override
    public String toString()
    {
        return "Pair{" +
                "firstValue=" + firstValue +
                ", secondValue=" + secondValue +
                '}';
    }

    static <T extends Number, R extends T> Pair<T, R> createPair(T value1, R value2)
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
