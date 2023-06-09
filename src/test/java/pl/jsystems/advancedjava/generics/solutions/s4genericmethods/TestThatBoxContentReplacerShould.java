package pl.jsystems.advancedjava.generics.solutions.s4genericmethods;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TestThatBoxContentReplacerShould
{
    private final BoxContentReplacer boxContentReplacer = new BoxContentReplacer();

    @Test
    void replaceContentsOfNonEmptyBox()
    {
        // GIVEN
        String firstContent = "Siekierka";
        String newContent = "Kijek";
        String username = "Stryjek";
        Box<String> box = new Box<>();
        box.putInto(firstContent);

        // WHEN
        boxContentReplacer.replaceUsing(box, newContent, username);

        // THEN
        assertThat(box.peekAtContent()).isEqualTo(newContent);
    }

    @Test
    void returnOldContentsAfterReplacement()
    {
        // GIVEN
        String firstContent = "Siekierka";
        String newContent = "Kijek";
        String username = "Stryjek";
        Box<String> box = new Box<>();
        box.putInto(firstContent);

        // WHEN
        String oldContent = boxContentReplacer.replaceUsing(box, newContent, username);

        // THEN
        assertThat(oldContent).isEqualTo(firstContent);
    }

    @Test
    void throwExceptionWhenBoxIsNull()
    {
        // GIVEN
        String newContent = "not important";
        String username = "Stryjek";
        Box<String> box = null;

        // WHEN
        ThrowingCallable replaceMethod = () -> boxContentReplacer.replaceUsing(box, newContent, username);

        // THEN
        assertThatThrownBy(replaceMethod)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Box cannot be null.");
    }

    @Test
    void throwExceptionWhenNewContentIsNull()
    {
        // GIVEN
        String firstContent = "not important";
        String newContent = null;
        String username = "Stryjek";
        Box<String> box = new Box<>();
        box.putInto(firstContent);

        // WHEN
        ThrowingCallable replaceMethod = () -> boxContentReplacer.replaceUsing(box, newContent, username);

        // THEN
        assertThatThrownBy(replaceMethod)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("New content cannot be null.");
    }

    @Test
    void throwExceptionWhenBoxIsEmpty()
    {
        // GIVEN
        String firstContent = null;
        String newContent = "not important";
        Box<String> box = new Box<>();
        String username = "Stryjek";
        box.putInto(firstContent);

        // WHEN
        ThrowingCallable replaceMethod = () -> boxContentReplacer.replaceUsing(box, newContent, username);

        // THEN
        assertThatThrownBy(replaceMethod)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Box cannot be empty.");
    }

    @Test
    void throwExceptionWhenUsernameIsNull()
    {
        // GIVEN
        String firstContent = "first content";
        String newContent = "not important";
        Box<String> box = new Box<>();
        String username = null;
        box.putInto(firstContent);

        // WHEN
        ThrowingCallable replaceMethod = () -> boxContentReplacer.replaceUsing(box, newContent, username);

        // THEN
        assertThatThrownBy(replaceMethod)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Username has to be defined.");
    }

    @Test
    void throwExceptionWhenUsernameIsBlank()
    {
        // GIVEN
        String firstContent = "first content";
        String newContent = "not important";
        Box<String> box = new Box<>();
        String username = " ";
        box.putInto(firstContent);

        // WHEN
        ThrowingCallable replaceMethod = () -> boxContentReplacer.replaceUsing(box, newContent, username);

        // THEN
        assertThatThrownBy(replaceMethod)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Username has to be defined.");
    }
}
