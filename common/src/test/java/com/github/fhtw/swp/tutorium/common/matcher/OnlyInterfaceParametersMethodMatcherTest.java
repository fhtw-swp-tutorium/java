package com.github.fhtw.swp.tutorium.common.matcher;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.hamcrest.CoreMatchers.is;

public class OnlyInterfaceParametersMethodMatcherTest {

    private Matcher<Method> sut;

    @Before
    public void setUp() throws Exception {
        sut = new OnlyInterfaceParametersMethodMatcher();
    }

    @Test
    public void testShouldMatchIfNotParameters() throws Exception {
        final Method method = TestClass.class.getMethod("methodWithNoParameter");

        final boolean result = sut.matches(method);

        Assert.assertThat(result, is(true));
    }

    @Test
    public void testShouldMatchIfAllParamtersAreInterfaces() throws Exception {
        final Method method = TestClass.class.getMethod("methodWithInterfaceParameter", TestInterface.class);

        final boolean result = sut.matches(method);

        Assert.assertThat(result, is(true));
    }

    @Test
    public void testShouldNotMatchIfAnyParameterIsClass() throws Exception {
        final Method method = TestClass.class.getMethod("methodWithClass", TestClass.class);

        final boolean result = sut.matches(method);

        Assert.assertThat(result, is(false));
    }

}