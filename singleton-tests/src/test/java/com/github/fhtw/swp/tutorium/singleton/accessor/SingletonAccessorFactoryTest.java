package com.github.fhtw.swp.tutorium.singleton.accessor;

import com.github.fhtw.swp.tutorium.singleton.matcher.FieldSingleton;
import com.github.fhtw.swp.tutorium.singleton.matcher.MethodSingleton;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;

public class SingletonAccessorFactoryTest {

    private SingletonAccessorFactory singletonAccessorFactory;

    @Before
    public void setUp() throws Exception {
        singletonAccessorFactory = new SingletonAccessorFactory();
    }

    @Test
    public void testShouldCreateAccessorForFieldSingleton() throws Exception {

        final SingletonAccessor singletonAccessor = singletonAccessorFactory.create(FieldSingleton.class);

        final Object instance = singletonAccessor.getInstance();

        Assert.assertThat(instance, sameInstance(FieldSingleton.INSTANCE));
    }

    @Test
    public void testShouldCreateAccessorForMethodSingleton() throws Exception {

        final SingletonAccessor singletonAccessor = singletonAccessorFactory.create(MethodSingleton.class);

        final Object instance = singletonAccessor.getInstance();

        Assert.assertThat(instance, sameInstance(MethodSingleton.getInstance()));
    }

    @Test
    public void testShouldCreateDummyAccessorForNoSingleton() throws Exception {

        final SingletonAccessor singletonAccessor = singletonAccessorFactory.create(Object.class);

        final Object firstInstance = singletonAccessor.getInstance();
        final Object secondInstance = singletonAccessor.getInstance();

        Assert.assertThat(firstInstance, not(sameInstance(secondInstance)));
    }
}