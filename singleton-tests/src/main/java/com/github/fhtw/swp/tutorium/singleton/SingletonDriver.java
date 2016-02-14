package com.github.fhtw.swp.tutorium.singleton;

import com.github.fhtw.swp.tutorium.common.ConfigurationFactory;
import com.github.fhtw.swp.tutorium.common.matcher.AnnotatedElementExistsMatcher;
import com.github.fhtw.swp.tutorium.common.matcher.PrivateConstructorMatcher;
import com.github.fhtw.swp.tutorium.singleton.accessor.SingletonAccessor;
import com.github.fhtw.swp.tutorium.singleton.accessor.SingletonAccessorFactory;
import org.hamcrest.Matcher;
import org.reflections.Configuration;
import org.reflections.Reflections;

import java.util.Collection;
import java.util.Set;

import static com.github.fhtw.swp.tutorium.singleton.matcher.FieldSingletonMatcher.isFieldSingleton;
import static com.github.fhtw.swp.tutorium.singleton.matcher.MethodSingletonMatcher.isMethodSingleton;
import static org.hamcrest.Matchers.anyOf;

public class SingletonDriver {

    private Set<Class<?>> singletons;
    private final SingletonAccessorFactory singletonAccessorFactory;

    public SingletonDriver() {

        final Configuration configuration = new ConfigurationFactory().create();

        final Reflections reflections = new Reflections(configuration);
        singletons = reflections.getTypesAnnotatedWith(Singleton.class);

        singletonAccessorFactory = new SingletonAccessorFactory();
    }

    public Set<Class<?>> getSingletonClasses() {
        return singletons;
    }

    public Matcher<Collection<Class<?>>> getSizeMatcher() {
        return new AnnotatedElementExistsMatcher(Singleton.class);
    }

    public Matcher<Class<?>> getAccessorMatcher() {
        return anyOf(
                isFieldSingleton(),
                isMethodSingleton()
        );
    }

    public Matcher<Class<?>> getContructorMatcher() {
        return new PrivateConstructorMatcher();
    }

    public SingletonAccessor getSingletonAccessor(Class<?> singletonClass) {
        return singletonAccessorFactory.create(singletonClass);
    }
}
