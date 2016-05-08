package org.hamcrest.reflection;

import org.hamcrest.Matcher;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.assertThat;

public class ParameterTypeMatcherTest {

    @Test
    public void shouldMatchExpectedParameters() throws Exception {

        final Method method = Bar.class.getMethod("methodWithClass", Bar.class);
        final Matcher<Method> sut = ParameterTypeMatcher.hasParameterTypes(Bar.class);

        assertThat(method, sut);
    }
}