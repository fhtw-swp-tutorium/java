package com.github.fhtw.swp.tutorium.singleton;

import com.github.fhtw.swp.tutorium.common.matcher.PrivateConstructorMatcher;
import com.github.fhtw.swp.tutorium.singleton.accessor.SingletonAccessor;
import com.github.fhtw.swp.tutorium.singleton.accessor.SingletonAccessorFactory;
import org.hamcrest.Matcher;

import static com.github.fhtw.swp.tutorium.singleton.matcher.FieldSingletonMatcher.isFieldSingleton;
import static com.github.fhtw.swp.tutorium.singleton.matcher.MethodSingletonMatcher.isMethodSingleton;
import static org.hamcrest.Matchers.anyOf;

public class SingletonDriver {

    private final SingletonAccessorFactory singletonAccessorFactory;

    public SingletonDriver() {
        singletonAccessorFactory = new SingletonAccessorFactory();
    }

    public Matcher<Class<?>> getAccessorMatcher() {
        return anyOf(
                isFieldSingleton(),
                isMethodSingleton()
        );
    }

    public Matcher<Class<?>> getConstructorMatcher() {
        return new PrivateConstructorMatcher();
    }

    public SingletonAccessor getSingletonAccessor(Class<?> singletonClass) {
        return singletonAccessorFactory.create(singletonClass);
    }
}
