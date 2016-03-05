package com.github.fhtw.swp.tutorium.reflection.impl;

import com.github.fhtw.swp.tutorium.NullFactory;
import com.github.fhtw.swp.tutorium.reflection.ClassInstanceFactory;

import java.lang.annotation.Annotation;
import java.util.function.Function;

public class AnnotatedClassInstanceFactory<T extends Annotation> implements ClassInstanceFactory {

    private final Class<T> annotationType;
    private final Function<T, Class<?>> factoryMethod;

    public AnnotatedClassInstanceFactory(Class<T> annotationType, Function<T, Class<?>> factoryMethod) {
        this.annotationType = annotationType;
        this.factoryMethod = factoryMethod;
    }

    @Override
    public Object create(Class<?> type) {

        final ClassInstanceFactory delegate = getFactory(type);

        return delegate.create(type);
    }

    private ClassInstanceFactory getFactory(Class<?> type) {

        final Class<?> customFactoryClass = getCustomFactoryClass(type);

        if (shouldUse(customFactoryClass)) {
            return new ClassInstanceFactoryProxy(customFactoryClass);
        } else {
            return new SimpleClassInstanceFactory();
        }
    }

    private Class<?> getCustomFactoryClass(Class<?> type) {
        final T annotation = type.getAnnotation(this.annotationType);
        return factoryMethod.apply(annotation);
    }

    private boolean shouldUse(Class<?> factoryClass) {
        final boolean isNullFactory = NullFactory.class == factoryClass;
        return !isNullFactory;
    }
}
