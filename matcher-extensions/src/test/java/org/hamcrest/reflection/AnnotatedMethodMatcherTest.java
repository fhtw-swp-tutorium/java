package org.hamcrest.reflection;

import org.hamcrest.Matcher;
import org.junit.Test;

import static org.junit.Assert.*;

public class AnnotatedMethodMatcherTest {

    @Test
    public void shouldFindAnnotatedMethodInClass() throws Exception {

        final Matcher<Class<?>> sut = AnnotatedMethodMatcher.hasSingleMethodWithAnnotation(Baz.class);

        assertThat(Bar.class, sut);
    }
}