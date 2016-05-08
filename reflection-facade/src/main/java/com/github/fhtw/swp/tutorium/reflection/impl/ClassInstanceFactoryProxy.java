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
    public <T> T create(Class<T> type) {
        final Factory factory = instantiateFactory();

        final Object constructedInstance = factory.getInstance();

        if (!type.isInstance(constructedInstance)) {
            final String errorMessage = String.format("%s does not create instances of %s", factoryClass.getSimpleName(), type);
            throw new IllegalArgumentException(errorMessage);
        }

        return (T) constructedInstance;
    }

    private Factory instantiateFactory() {
        try {
            return factoryClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }
}
