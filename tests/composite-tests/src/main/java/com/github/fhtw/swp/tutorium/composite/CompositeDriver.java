package com.github.fhtw.swp.tutorium.composite;

import org.reflections.ReflectionUtils;

import java.lang.reflect.Method;

import static org.reflections.ReflectionUtils.withAnnotation;

public class CompositeDriver {

    public Class<?> getDefinedComponentType(Class<?> compositeType) {
        return compositeType.getAnnotation(Composite.class).value();
    }

    public Method getAddComponentMethod(Class<?> compositeType) {
        return ReflectionUtils.getAllMethods(compositeType, withAnnotation(AddComponent.class)).stream().findFirst().get();
    }
}
