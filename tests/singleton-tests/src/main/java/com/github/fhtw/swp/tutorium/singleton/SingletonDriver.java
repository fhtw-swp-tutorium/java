package com.github.fhtw.swp.tutorium.singleton;

import com.github.fhtw.swp.tutorium.singleton.accessor.SingletonProxy;
import com.github.fhtw.swp.tutorium.singleton.accessor.SingletonProxyFactory;
import org.hamcrest.Matcher;
import org.hamcrest.reflection.PrivateConstructorMatcher;

import static com.github.fhtw.swp.tutorium.singleton.matcher.FieldSingletonMatcher.isFieldSingleton;
import static com.github.fhtw.swp.tutorium.singleton.matcher.MethodSingletonMatcher.isMethodSingleton;
import static org.hamcrest.Matchers.anyOf;

public class SingletonDriver {

    private final SingletonProxyFactory singletonProxyFactory;

    public SingletonDriver() {
        singletonProxyFactory = new SingletonProxyFactory();
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

    public SingletonProxy getSingletonAccessor(Class<?> singletonClass) {
        return singletonProxyFactory.create(singletonClass);
    }
}
