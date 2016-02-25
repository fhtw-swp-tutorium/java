package com.github.fhtw.swp.tutorium.common.matcher;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class AlwaysFalseMatcherTest {

    private Matcher<Object> sut;

    @Before
    public void setUp() throws Exception {
        sut = new AlwaysFalseMatcher<>();
    }

    @Test
    public void testShouldNeverMatch() throws Exception {
        final boolean newObjectResult = sut.matches(new Object());
        final boolean nullResult = sut.matches(null);

        Assert.assertThat(newObjectResult, is(false));
        Assert.assertThat(nullResult, is(false));
    }
}