package com.github.fhtw.swp.tutorium.singleton.accessor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class MethodSingletonProxy implements SingletonProxy {

    private final Method accessorMethod;

    MethodSingletonProxy(Method accessorMethod) {
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
