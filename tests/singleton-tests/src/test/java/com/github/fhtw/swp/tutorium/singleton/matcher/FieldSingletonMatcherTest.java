package com.github.fhtw.swp.tutorium.singleton.matcher;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

public class FieldSingletonMatcherTest {

    private FieldSingletonMatcher fieldSingletonMatcher;

    @Before
    public void setUp() throws Exception {
        fieldSingletonMatcher = new FieldSingletonMatcher();
    }

    @Test
    public void testShouldMatchFieldSingleton() throws Exception {

        final boolean result = fieldSingletonMatcher.matches(FieldSingleton.class);

        Assert.assertThat(result, is(true));
    }
}