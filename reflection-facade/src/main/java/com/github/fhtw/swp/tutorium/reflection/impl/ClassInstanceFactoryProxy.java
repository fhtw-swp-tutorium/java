package com.github.fhtw.swp.tutorium.reflection.impl;

import com.github.fhtw.swp.tutorium.Factory;
import com.github.fhtw.swp.tutorium.reflection.ClassInstanceFactory;

/**
 * A {@link ClassInstanceFactory} that proxies a given factory class and delegates the creation to it.
 */
@SuppressWarnings("unchecked")
public class ClassInstanceFactoryProxy implements ClassInstanceFactory {

    private final Class<? extends Factory> factoryClass;

    public ClassInstanceFactoryProxy(Class<? extends Factory> factoryClass) {
        this.factoryClass = factoryClass;
    }

    @Override
    public Object create(Class<?> type) {

        final Factory factory = instantiateFactory();
        return factory.getInstance();
    }

    private Factory instantiateFactory() {
        try {
            return factoryClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }
}
