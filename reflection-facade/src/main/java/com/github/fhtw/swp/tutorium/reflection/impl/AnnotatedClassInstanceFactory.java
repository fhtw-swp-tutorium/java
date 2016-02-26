package com.github.fhtw.swp.tutorium.reflection.impl;

import com.github.fhtw.swp.tutorium.reflection.ClassInstanceFactory;

import java.lang.annotation.Annotation;
import java.util.function.Function;

public class AnnotatedClassInstanceFactory<T extends Annotation> implements ClassInstanceFactory {

    private final Class<T> annotationType;
    private final Function<T, Class<?>> factoryMethod;
    private final Class<?> nullFactory;

    public AnnotatedClassInstanceFactory(Class<T> annotationType, Function<T, Class<?>> factoryMethod, Class<?> nullFactory) {
        this.annotationType = annotationType;
        this.factoryMethod = factoryMethod;
        this.nullFactory = nullFactory;
    }

    @Override
    public Object create(Class<?> type) {

        final Class<?> factoryClass = getFactoryClass(type);

        if (isNullFactory(factoryClass)) {
            return new SimpleClassInstanceFactory().create(type);
        } else {
            return new DelegatingClassInstanceFactory(factoryClass).create(type);
        }
    }

    private Class<?> getFactoryClass(Class<?> type) {
        final T annotation = type.getAnnotation(this.annotationType);
        return factoryMethod.apply(annotation);
    }

    private boolean isNullFactory(Class<?> factoryClass) {
        return factoryClass.equals(nullFactory);
    }
}
