package pl.jsystems.advancedjava.generics.solutions.s2genericmessageid;

record Message<ID, T>(ID id, T content)
{
    Message
    {
        validate(id, "Id");
        validate(content, "Content");
    }

    private void validate(Object content, String fieldName)
    {
        if (content == null)
        {
            throw new IllegalArgumentException(fieldName + " cannot be null.");
        }
    }

    @Override
    public String toString()
    {
        return "Message{" +
                "id=" + id +
                ", content=" + content +
                '}';
    }
}
