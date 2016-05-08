package com.github.fhtw.swp.tutorium.composite.proxy.factory;

import com.github.fhtw.swp.tutorium.composite.proxy.CompositeProxy;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class CompositeProxyFactoryTest {

    private CompositeProxyFactory sut;

    @Before
    public void setUp() throws Exception {
        sut = new CompositeProxyFactory();
    }

    @Test
    public void shouldCreateCompositeProxyFromAnnotatedClass() throws Exception {

        final CompositeProxy compositeProxy = sut.create(CompositeStub.class);

        assertThat(compositeProxy, is(notNullValue()));
        // TODO enhance this test once the dependencies of SUT are mockable
    }
}