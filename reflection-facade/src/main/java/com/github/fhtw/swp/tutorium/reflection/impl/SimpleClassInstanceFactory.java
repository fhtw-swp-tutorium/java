package com.github.fhtw.swp.tutorium.reflection.impl;

import com.github.fhtw.swp.tutorium.reflection.ClassInstanceFactory;

public class SimpleClassInstanceFactory implements ClassInstanceFactory {
    @Override
    public Object create(Class<?> type) {
        try {
            return type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }
}
