package com.github.fhtw.swp.tutorium.common.matcher;

import com.github.fhtw.swp.tutorium.common.Optionals;
import com.github.fhtw.swp.tutorium.common.SwpTestContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class PrivateConstructorMatcherTest {

    private PrivateConstructorMatcher privateConstructorMatcher;

    @Before
    public void setUp() throws Exception {
        privateConstructorMatcher = new PrivateConstructorMatcher();
    }

    @Test
    public void testShouldNotMatchIfAnyConstructorIsNotPrivate() throws Exception {
        final boolean result = privateConstructorMatcher.matches(SwpTestContext.class);

        Assert.assertThat(result, is(false));
    }

    @Test
    public void testShouldMatchIfAllConstructorsArePrivate() throws Exception {
        final boolean result = privateConstructorMatcher.matches(Optionals.class);

        Assert.assertThat(result, is(true));
    }
}