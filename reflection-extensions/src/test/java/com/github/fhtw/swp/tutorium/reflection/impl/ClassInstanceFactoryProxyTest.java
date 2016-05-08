package com.github.fhtw.swp.tutorium.reflection.impl;

import com.github.fhtw.swp.tutorium.reflection.ClassInstanceFactory;
import com.github.fhtw.swp.tutorium.reflection.OtherThingFactory;
import com.github.fhtw.swp.tutorium.reflection.SimpleThing;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ClassInstanceFactoryProxyTest {

    private ClassInstanceFactory sut;

    @Before
    public void setUp() throws Exception {
        sut = new ClassInstanceFactoryProxy(OtherThingFactory.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfGivenFactoryDoesNotCreateInstanceOfDeclaredType() throws Exception {
        sut.create(SimpleThing.class);
    }

    @Test
    public void shouldNotThrowExceptionIfConstructedTypeIsInstanceOfGivenClass() throws Exception {
        final Object objectInstance = sut.create(Object.class);

        Assert.assertThat(objectInstance, is(notNullValue()));
    }
}