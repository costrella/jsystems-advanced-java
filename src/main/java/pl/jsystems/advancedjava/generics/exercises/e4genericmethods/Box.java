package pl.jsystems.advancedjava.generics.exercises.e4genericmethods;

class Box<T>
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
