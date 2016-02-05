package com.github.fhtw.tutorium.swp.singleton;

import static com.github.fhtw.swp.tutorium.common.Matchers.exist;
import static com.github.fhtw.swp.tutorium.common.Matchers.hasNoPublicConstructor;
import static com.github.fhtw.tutorium.swp.singleton.Matchers.providesSingletonAccessor;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.reflections.Configuration;
import org.reflections.Reflections;

import com.github.fhtw.swp.tutorium.common.ConfigurationFactory;
import com.github.fhtw.swp.tutorium.singleton.Singleton;

/**
 * @author Thomas Eizinger, Senacor Technologies AG.
 */
public class SingletonTest {

    private Set<Class<?>> singletons;
    private SingletonAccessorFactory singletonAccessorFactory;

    @Before
    public void setUp() throws Exception {

        final Configuration configuration = new ConfigurationFactory().create();

        final Reflections reflections = new Reflections(configuration);
        singletons = reflections.getTypesAnnotatedWith(Singleton.class);

        singletonAccessorFactory = new SingletonAccessorFactory();
    }

    @Test
    public void testSingletonsShouldExist() throws Exception {
        assertThat(singletons, exist());
    }

    @Test
    public void testSingletonShouldProvideInstanceFieldOrAccessorMethod() throws Exception {

        for (Class<?> singleton : singletons) {
            assertThat(singleton, providesSingletonAccessor());
        }
    }

    @Test
    public void testSingletonShouldHaveNonPublicConstructor() throws Exception {

        for (Class<?> singleton : singletons) {
            assertThat(singleton, hasNoPublicConstructor());
        }
    }

    @Test
    public void testSingletonAccessorShouldReturnSameInstance() throws Exception {

        for (Class<?> singleton : singletons) {

            final SingletonAccessor singletonAccessor = singletonAccessorFactory.create(singleton);

            final Object instance1 = singletonAccessor.getInstance();
            final Object instance2 = singletonAccessor.getInstance();

            assertThat(instance1 == instance2, is(true));
        }
    }
}

