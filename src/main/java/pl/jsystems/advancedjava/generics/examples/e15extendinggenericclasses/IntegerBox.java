package pl.jsystems.advancedjava.generics.examples.e15extendinggenericclasses;

class IntegerBox extends NumberBox<Integer>
{
    boolean isContentEven() {
        return peekAtContent() % 2 == 0;
    }
}
