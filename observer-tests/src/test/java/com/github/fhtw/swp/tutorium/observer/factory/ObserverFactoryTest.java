package com.github.fhtw.swp.tutorium.observer.factory;

import com.github.fhtw.swp.tutorium.observer.ComplexTestSubject;
import com.github.fhtw.swp.tutorium.observer.CountingInvocationHandler;
import com.github.fhtw.swp.tutorium.observer.Observer;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class ObserverFactoryTest {

    @Test
    public void testShouldCreateObserverForSubject() throws Exception {
        final Object observer = ObserverFactory.create(ComplexTestSubject.class, new CountingInvocationHandler());

        Assert.assertThat(observer, is(notNullValue()));
        Assert.assertThat(observer, is(instanceOf(Observer.class)));
    }
}