package org.hamcrest.reflection;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.assertThat;

public class ParameterCountMethodMatcherTest {

    private Matcher<Method> sut;

    @Before
    public void setUp() throws Exception {
        sut = new ParameterCountMethodMatcher(1);
    }

    @Test
    public void testShouldMatchParameterCount() throws Exception {
        final Method method = Bar.class.getMethod("methodWithInterfaceParameter", Foo.class);

        assertThat(method, sut);
    }
}