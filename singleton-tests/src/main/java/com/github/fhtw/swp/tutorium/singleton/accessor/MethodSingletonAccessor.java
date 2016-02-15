package com.github.fhtw.swp.tutorium.singleton.accessor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class MethodSingletonAccessor implements SingletonAccessor {

    private final Method accessorMethod;

    MethodSingletonAccessor(Method accessorMethod) {
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

}
