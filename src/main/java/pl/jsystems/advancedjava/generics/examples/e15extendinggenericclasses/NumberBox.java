package pl.jsystems.advancedjava.generics.examples.e15extendinggenericclasses;

import java.util.Objects;

class NumberBox<T extends Number> extends Box<T> implements Comparable<Box<? extends Number>>
{
    boolean isContentPositive() {
        return peekAtContent().doubleValue() > 0d;
    }

    boolean isZero() {
        return peekAtContent().doubleValue() == 0;
    }

    boolean isNegative() {
        return !isContentPositive();
    }

    @Override
    public int compareTo(Box<? extends Number> o)
    {
        Objects.requireNonNull(o, "Box cannot be null.");
        if (peekAtContent() == null && o.peekAtContent() == null) {
            return 0;
        } else if (o.peekAtContent() == null) {
            return 1;
        } else if (peekAtContent() == null) {
            return -1;
        }
        return Double.compare(peekAtContent().doubleValue(), o.peekAtContent().doubleValue());
    }
}
