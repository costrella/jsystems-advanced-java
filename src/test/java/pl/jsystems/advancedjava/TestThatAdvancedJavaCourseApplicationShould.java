package pl.jsystems.advancedjava;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatNoException;

class TestThatAdvancedJavaCourseApplicationShould
{
    @Test
    void runWithoutIssues()
    {
        // GIVEN
        AdvancedJavaCourseApplication experimentsApp = new AdvancedJavaCourseApplication();

        // WHEN
        ThrowingCallable testRunner = experimentsApp::run;

        // THEN
        assertThatNoException().isThrownBy(testRunner);
    }
}
