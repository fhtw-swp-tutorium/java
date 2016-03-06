package com.github.fhtw.swp.tutorium.singleton.accessor;

import com.github.fhtw.swp.tutorium.singleton.matcher.FieldSingleton;
import com.github.fhtw.swp.tutorium.singleton.matcher.MethodSingleton;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;

public class SingletonProxyFactoryTest {

    private SingletonProxyFactory singletonProxyFactory;

    @Before
    public void setUp() throws Exception {
        singletonProxyFactory = new SingletonProxyFactory();
    }

    @Test
    public void testShouldCreateAccessorForFieldSingleton() throws Exception {

        final SingletonProxy singletonProxy = singletonProxyFactory.create(FieldSingleton.class);

        final Object instance = singletonProxy.getInstance();

        Assert.assertThat(instance, sameInstance(FieldSingleton.INSTANCE));
    }

    @Test
    public void testShouldCreateAccessorForMethodSingleton() throws Exception {

        final SingletonProxy singletonProxy = singletonProxyFactory.create(MethodSingleton.class);

        final Object instance = singletonProxy.getInstance();

        Assert.assertThat(instance, sameInstance(MethodSingleton.getInstance()));
    }

    @Test
    public void testShouldCreateDummyAccessorForNoSingleton() throws Exception {

        final SingletonProxy singletonProxy = singletonProxyFactory.create(Object.class);

        final Object firstInstance = singletonProxy.getInstance();
        final Object secondInstance = singletonProxy.getInstance();

        Assert.assertThat(firstInstance, not(sameInstance(secondInstance)));
    }
}