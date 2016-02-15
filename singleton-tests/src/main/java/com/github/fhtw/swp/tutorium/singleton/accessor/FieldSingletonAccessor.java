package com.github.fhtw.swp.tutorium.singleton.accessor;

import java.lang.reflect.Field;

class FieldSingletonAccessor implements SingletonAccessor {

    private final Field accessorField;

    FieldSingletonAccessor(Field accessorField) {
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
