package com.github.fhtw.swp.tutorium.singleton.accessor;

import java.lang.reflect.Field;

class FieldSingletonProxy implements SingletonProxy {

    private final Field accessorField;

    FieldSingletonProxy(Field accessorField) {
        this.accessorField = accessorField;
    }

    @Override
    public Object getInstance() {
        try {
            return accessorField.get(null);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Failed to access field in singleton class", e);
        }
    }

}
