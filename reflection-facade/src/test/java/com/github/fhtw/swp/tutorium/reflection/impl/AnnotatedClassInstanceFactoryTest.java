package com.github.fhtw.swp.tutorium.reflection.impl;

import com.github.fhtw.swp.tutorium.observer.Subject;
import com.github.fhtw.swp.tutorium.reflection.ClassInstanceFactory;
import com.github.fhtw.swp.tutorium.reflection.ComplexTestSubject;
import com.github.fhtw.swp.tutorium.reflection.SimpleTestSubject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class AnnotatedClassInstanceFactoryTest {

    private ClassInstanceFactory sut;

    @Before
    public void setUp() throws Exception {
        sut = new AnnotatedClassInstanceFactory<>(Subject.class, Subject::factory, Subject.None.class);
    }

    @Test
    public void testShouldUseFactoryClassIfGiven() throws Exception {

        final Object subject = sut.create(ComplexTestSubject.class);

        Assert.assertThat(subject, is(notNullValue()));
        Assert.assertThat(subject, is(instanceOf(ComplexTestSubject.class)));
    }

    @Test
    public void testShouldUseDefaultConstructorIfNoFactoryClassIsGiven() throws Exception {

        final Object subject = sut.create(SimpleTestSubject.class);

        Assert.assertThat(subject, is(notNullValue()));
        Assert.assertThat(subject, is(instanceOf(SimpleTestSubject.class)));
    }

}