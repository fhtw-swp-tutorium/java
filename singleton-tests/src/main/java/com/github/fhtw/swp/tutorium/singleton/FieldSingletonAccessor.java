package com.github.fhtw.swp.tutorium.singleton;

import java.lang.reflect.Field;

class FieldSingletonAccessor implements SingletonAccessor {

    private final Class<?> singletonClass;
    private final Field accessorField;

    FieldSingletonAccessor(Class<?> singletonClass, Field accessorField) {
        this.singletonClass = singletonClass;
        this.accessorField = accessorField;
    }

    @Override
    public Object getInstance() {
        try {
            return accessorField.get(singletonClass);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Failed to access field in singleton class", e);
        }
    }

    @Override
    public boolean hasSingletonAccessor() {
        return true;
    }
}
