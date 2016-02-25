package com.github.fhtw.swp.tutorium.common.matcher;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.hamcrest.CoreMatchers.is;

public class ParameterCountMethodMatcherTest {

    private Matcher<Method> sut;

    @Before
    public void setUp() throws Exception {
        sut = new ParameterCountMethodMatcher(1);
    }

    @Test
    public void testShouldMatchParameterCount() throws Exception {
        final Method method = TestClass.class.getMethod("methodWithInterfaceParameter", TestInterface.class);

        final boolean result = sut.matches(method);

        Assert.assertThat(result, is(true));
    }
}