package org.hamcrest.reflection;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PrivateConstructorMatcherTest {

    private PrivateConstructorMatcher privateConstructorMatcher;

    @Before
    public void setUp() throws Exception {
        privateConstructorMatcher = new PrivateConstructorMatcher();
    }

    @Test
    public void testShouldNotMatchIfAnyConstructorIsNotPrivate() throws Exception {
        final boolean result = privateConstructorMatcher.matches(NoPrivateCtor.class);

        assertThat(result, is(false));
    }

    @Test
    public void testShouldMatchIfAllConstructorsArePrivate() throws Exception {
        final boolean result = privateConstructorMatcher.matches(OnlyPrivateCtor.class);

        assertThat(result, is(true));
    }

    public static class NoPrivateCtor {

    }

    public static class OnlyPrivateCtor {
        private OnlyPrivateCtor() { }
    }
}