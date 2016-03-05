package com.github.fhtw.swp.tutorium.reflection.impl;

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

        final Class<?> factoryClass = getFactoryClass(type);

        if (factoryClass == null) {
            return new SimpleClassInstanceFactory().create(type);
        } else {
            return new ClassInstanceFactoryProxy(factoryClass).create(type);
        }
    }

    private Class<?> getFactoryClass(Class<?> type) {
        final T annotation = type.getAnnotation(this.annotationType);
        return factoryMethod.apply(annotation);
    }
}
