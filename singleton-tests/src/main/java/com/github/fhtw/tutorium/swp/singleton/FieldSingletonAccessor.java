package com.github.fhtw.tutorium.swp.singleton;

import java.lang.reflect.Field;

/**
 * @author Thomas Eizinger, Senacor Technologies AG.
 */
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
    public boolean HasSingletonAccessor() {
        return true;
    }
}
