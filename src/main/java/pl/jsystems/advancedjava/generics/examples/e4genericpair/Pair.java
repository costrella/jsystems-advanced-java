package pl.jsystems.advancedjava.generics.examples.e4genericpair;

class Pair<VALUE1, VALUE2>
{
    private final VALUE1 firstValue;
    private final VALUE2 secondValue;

    Pair(VALUE1 firstValue, VALUE2 secondValue)
    {
        validate(firstValue, "First");
        validate(secondValue, "Second");
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    private void validate(Object value, String paramName)
    {
        if (value == null)
        {
            throw new IllegalArgumentException(paramName + " value cannot be null.");
        }
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
}