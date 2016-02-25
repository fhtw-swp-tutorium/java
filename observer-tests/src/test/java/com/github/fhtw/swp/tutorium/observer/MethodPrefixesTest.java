package com.github.fhtw.swp.tutorium.observer;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class MethodPrefixesTest {

    @Test
    public void testShouldMatchRegisterMethod() throws Exception {

        final Method registerMethod = MethodPrefixes.REGISTER.getMethodOn(SimpleTestSubject.class);

        Assert.assertThat(registerMethod, is(notNullValue()));
    }

    @Test
    public void testShouldMatchUpdateMethod() throws Exception {

        final Method registerMethod = MethodPrefixes.UPDATE.getMethodOn(SimpleTestSubject.class);

        Assert.assertThat(registerMethod, is(notNullValue()));
    }

    @Test
    public void testShouldMatchUnRegisterMethod() throws Exception {

        final Method registerMethod = MethodPrefixes.UNREGISTER.getMethodOn(SimpleTestSubject.class);

        Assert.assertThat(registerMethod, is(notNullValue()));
    }
}