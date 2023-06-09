package pl.jsystems.advancedjava.generics.solutions.s4genericmethods;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class BoxContentReplacer
{
    private static final Logger LOGGER = LoggerFactory.getLogger(BoxContentReplacer.class);

    <T> T replaceUsing(Box<T> box, T newContent, String username)
    {
        validateUsername(username);
        LOGGER.info("{} is replacing box contents from {} to {}", username, box, newContent);

        validateBox(box);
        validateNotNull(newContent, "New content cannot be null.");

        T oldContent = box.getOutContent();
        box.getOutContent();
        box.putInto(newContent);

        return oldContent;
    }

    private void validateUsername(String username)
    {
        if (username == null || username.isBlank())
        {
            throw new IllegalArgumentException("Username has to be defined.");
        }
    }

    private <T> void validateBox(Box<T> box)
    {
        validateNotNull(box, "Box cannot be null.");
        validateContents(box);
    }

    private <T> void validateNotNull(T somethingToValidate, String s)
    {
        if (somethingToValidate == null)
        {
            throw new IllegalArgumentException(s);
        }
    }

    private <T> void validateContents(Box<T> box)
    {
        if (!box.hasContent())
        {
            throw new IllegalStateException("Box cannot be empty.");
        }
    }
}
