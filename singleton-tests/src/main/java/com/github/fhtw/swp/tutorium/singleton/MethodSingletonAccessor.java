package com.github.fhtw.swp.tutorium.singleton;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class MethodSingletonAccessor implements SingletonAccessor {

    private final Class<?> singletonClass;
    private final Method accessorMethod;

    MethodSingletonAccessor(Class<?> singletonClass, Method accessorMethod) {
        this.singletonClass = singletonClass;
        this.accessorMethod = accessorMethod;
    }

    @Override
    public Object getInstance() {
        try {
            return accessorMethod.invoke(null);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException("Failed to access method in singleton class", e);
        }
    }

    @Override
    public boolean hasSingletonAccessor() {
        return true;
    }
}
