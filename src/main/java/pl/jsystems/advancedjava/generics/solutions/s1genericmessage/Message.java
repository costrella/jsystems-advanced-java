package pl.jsystems.advancedjava.generics.solutions.s1genericmessage;

class Message<T>
{
    private final T content;

    Message(T content)
    {
        validate(content);
        this.content = content;
    }

    private void validate(Object content)
    {
        if (content == null)
        {
            throw new IllegalArgumentException("Content cannot be null.");
        }
    }

    T getContent()
    {
        return content;
    }

    @Override
    public String toString()
    {
        return "Message{" +
                "content=" + content +
                '}';
    }
}
