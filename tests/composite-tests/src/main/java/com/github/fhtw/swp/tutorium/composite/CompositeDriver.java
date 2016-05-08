package com.github.fhtw.swp.tutorium.composite;

import com.github.fhtw.swp.tutorium.reflection.SingleAnnotatedMethodExtractor;

import java.lang.reflect.Method;

public class CompositeDriver {

    public Class<?> getDefinedComponentType(Class<?> compositeType) {
        return compositeType.getAnnotation(Composite.class).value();
    }

    public Method getAddComponentMethod(Class<?> compositeType) {
        return new SingleAnnotatedMethodExtractor(compositeType).getSingleAnnotatedMethod(AddComponent.class);
    }
}
