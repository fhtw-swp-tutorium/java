package com.github.fhtw.swp.tutorium.reflection.impl;

import com.github.fhtw.swp.tutorium.Factory;
import com.github.fhtw.swp.tutorium.NullFactory;
import com.github.fhtw.swp.tutorium.reflection.ClassInstanceFactory;

import java.lang.annotation.Annotation;
import java.util.function.Function;

public class AnnotatedClassInstanceFactory<A extends Annotation> implements ClassInstanceFactory {

    private final Class<A> annotationType;
    private final Function<A, Class<? extends Factory>> factoryMethod;

    public AnnotatedClassInstanceFactory(Class<A> annotationType, Function<A, Class<? extends Factory>> factoryMethod) {
        this.annotationType = annotationType;
        this.factoryMethod = factoryMethod;
    }

    private ClassInstanceFactory getFactory(Class<?> type) {

        final Class<? extends Factory> customFactoryClass = getCustomFactoryClass(type);

        if (shouldUse(customFactoryClass)) {
            return new ClassInstanceFactoryProxy(customFactoryClass);
        } else {
            return new SimpleClassInstanceFactory();
        }
    }

    private Class<? extends Factory> getCustomFactoryClass(Class<?> type) {
        final A annotation = type.getAnnotation(this.annotationType);
        return factoryMethod.apply(annotation);
    }

    private boolean shouldUse(Class<?> factoryClass) {
        final boolean isNullFactory = NullFactory.class == factoryClass;
        return !isNullFactory;
    }

    @Override
    public <T> T create(Class<T> type) {
        final ClassInstanceFactory delegate = getFactory(type);

        return delegate.create(type);
    }
}
