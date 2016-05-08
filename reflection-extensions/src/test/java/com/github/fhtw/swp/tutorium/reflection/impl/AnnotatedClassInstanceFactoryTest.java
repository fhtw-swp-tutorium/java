package com.github.fhtw.swp.tutorium.reflection.impl;

import com.github.fhtw.swp.tutorium.reflection.ClassInstanceFactory;
import com.github.fhtw.swp.tutorium.reflection.ComplexThing;
import com.github.fhtw.swp.tutorium.reflection.SimpleThing;
import com.github.fhtw.swp.tutorium.reflection.Thing;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class AnnotatedClassInstanceFactoryTest {

    private ClassInstanceFactory sut;

    @Before
    public void setUp() throws Exception {
        sut = new AnnotatedClassInstanceFactory<>(Thing.class, Thing::factory);
    }

    @Test
    public void testShouldUseFactoryClassIfGiven() throws Exception {

        final Object thing = sut.create(ComplexThing.class);

        Assert.assertThat(thing, is(notNullValue()));
        Assert.assertThat(thing, is(instanceOf(ComplexThing.class)));
    }

    @Test
    public void testShouldUseDefaultConstructorIfNoFactoryClassIsGiven() throws Exception {

        final Object thing = sut.create(SimpleThing.class);

        Assert.assertThat(thing, is(notNullValue()));
        Assert.assertThat(thing, is(instanceOf(SimpleThing.class)));
    }

}