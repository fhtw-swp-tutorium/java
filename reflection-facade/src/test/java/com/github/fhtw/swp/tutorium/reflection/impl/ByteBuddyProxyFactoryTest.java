package com.github.fhtw.swp.tutorium.reflection.impl;

import com.github.fhtw.swp.tutorium.reflection.CountingInvocationHandler;
import com.github.fhtw.swp.tutorium.reflection.Observer;
import com.github.fhtw.swp.tutorium.reflection.ProxyFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class ByteBuddyProxyFactoryTest {

    private ProxyFactory sut;

    @Before
    public void setUp() throws Exception {
        sut = new ByteBuddyProxyFactory();
    }

    @Test
    public void testShouldCreateProxyForInterface() throws Exception {

        final Object proxy = sut.create(Observer.class, new CountingInvocationHandler());

        Assert.assertThat(proxy, is(notNullValue()));
        Assert.assertThat(proxy, is(instanceOf(Observer.class)));
    }
}