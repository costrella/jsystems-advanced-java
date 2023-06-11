package pl.jsystems.advancedjava.generics.examples.e12multiplebounds;

// We want to only have numbers that can be compared.
// To understand the notation - try replacing T with some specific type
// for example: Box<Integer extends Number & Comparable<Integer>>
// That would mean that we have box of integers (that obviously extend from Number)
// and are comparable with other integers.
// It's very rarely used notation.
class Box<T extends Number & Comparable<T>>
{
    private T content;

    boolean hasContent()
    {
        return content != null;
    }

    // we could add check here, not to put anything if something is already here.
    // Also, null content should not be allowed
    void putInto(T content)
    {
        this.content = content;
    }

    // would be better to change that to Optional<T>
    T peekAtContent()
    {
        return content;
    }

    // would be better to change that to Optional<T>
    T getOutContent()
    {
        T result = content;
        content = null;
        return result;
    }

    @Override
    public String toString()
    {
        return "Box{" +
                "content=" + content +
                '}';
    }
}
