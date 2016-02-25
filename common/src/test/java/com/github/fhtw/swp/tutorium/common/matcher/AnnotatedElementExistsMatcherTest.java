package com.github.fhtw.swp.tutorium.common.matcher;

import com.github.fhtw.swp.tutorium.observer.Subject;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;

public class AnnotatedElementExistsMatcherTest {

    private Matcher<Collection<Class<?>>> sut;

    @Before
    public void setUp() throws Exception {
        sut = new AnnotatedElementExistsMatcher(Subject.class);

    }

    @Test
    public void testShouldMatchIfAnyElementIsGiven() throws Exception {
        final boolean result = sut.matches(Collections.singletonList(Object.class));

        Assert.assertThat(result, is(true));
    }

    @Test
    public void testShouldNotMatchIfNotElementIsGiven() throws Exception {
        final boolean result = sut.matches(Collections.emptyList());

        Assert.assertThat(result, is(false));
    }
}