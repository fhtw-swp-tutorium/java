package org.hamcrest.reflection;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class OnlyInterfaceParametersMethodMatcherTest {

    private Matcher<Method> sut;

    @Before
    public void setUp() throws Exception {
        sut = new OnlyInterfaceParametersMethodMatcher();
    }

    @Test
    public void testShouldMatchIfNotParameters() throws Exception {
        final Method method = Bar.class.getMethod("methodWithNoParameter");

        final boolean result = sut.matches(method);

        assertThat(result, is(true));
    }

    @Test
    public void testShouldMatchIfAllParamtersAreInterfaces() throws Exception {
        final Method method = Bar.class.getMethod("methodWithInterfaceParameter", Foo.class);

        final boolean result = sut.matches(method);

        assertThat(result, is(true));
    }

    @Test
    public void testShouldNotMatchIfAnyParameterIsClass() throws Exception {
        final Method method = Bar.class.getMethod("methodWithClass", Bar.class);

        final boolean result = sut.matches(method);

        assertThat(result, is(false));
    }

}