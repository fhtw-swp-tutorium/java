package com.github.fhtw.swp.tutorium.singleton.matcher;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

public class MethodSingletonMatcherTest {

    private MethodSingletonMatcher methodSingletonMatcher;

    @Before
    public void setUp() throws Exception {
        methodSingletonMatcher = new MethodSingletonMatcher();
    }

    @Test
    public void testShouldMatchMethodSingleton() throws Exception {

        final boolean result = methodSingletonMatcher.matches(MethodSingleton.class);

        Assert.assertThat(result, is(true));
    }
}