package com.github.fhtw.swp.tutorium.observer.factory;

import com.github.fhtw.swp.tutorium.observer.ComplexTestSubject;
import com.github.fhtw.swp.tutorium.observer.SimpleTestSubject;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class SubjectFactoryTest {

    @Test
    public void testShouldUseFactoryClassIfGiven() throws Exception {

        final Object subject = SubjectFactory.create(ComplexTestSubject.class);

        Assert.assertThat(subject, is(notNullValue()));
        Assert.assertThat(subject, is(instanceOf(ComplexTestSubject.class)));
    }

    @Test
    public void testShouldUseDefaultConstructorIfNoFactoryClassIsGiven() throws Exception {

        final Object subject = SubjectFactory.create(SimpleTestSubject.class);

        Assert.assertThat(subject, is(notNullValue()));
        Assert.assertThat(subject, is(instanceOf(SimpleTestSubject.class)));
    }
}