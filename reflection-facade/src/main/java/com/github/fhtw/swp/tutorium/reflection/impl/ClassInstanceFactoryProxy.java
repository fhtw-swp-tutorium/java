package com.github.fhtw.swp.tutorium.reflection.impl;

import com.github.fhtw.swp.tutorium.reflection.ClassInstanceFactory;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.reflections.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static java.lang.reflect.Modifier.PUBLIC;
import static org.reflections.ReflectionUtils.*;

/**
 * A {@link ClassInstanceFactory} that proxies a given factory class and delegates the creation to it.
 */
@SuppressWarnings("unchecked")
public class ClassInstanceFactoryProxy implements ClassInstanceFactory {

    private final Class<?> factoryClass;
    private final ClassInstanceFactory classInstanceFactory;

    public ClassInstanceFactoryProxy(Class<?> factoryClass) {
        this.factoryClass = factoryClass;
        this.classInstanceFactory = new SimpleClassInstanceFactory();
    }

    @Override
    public Object create(Class<?> type) {

        final Object factory = classInstanceFactory.create(factoryClass);
        final Method factoryMethod = getFactoryMethod(type);

        return invokeFactoryMethod(factory, factoryMethod);
    }

    private Method getFactoryMethod(Class<?> typeToConstruct) {

        final Predicate<Method> isFactoryMethod = Predicates.and(
                withParametersCount(0),
                withReturnType(typeToConstruct),
                withModifier(PUBLIC)
        );

        return ReflectionUtils
                .getAllMethods(factoryClass, isFactoryMethod)
                .stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No suitable method found on given factory class for subject"));
    }

    private Object invokeFactoryMethod(Object factory, Method factoryMethod) {
        try {
            return factoryMethod.invoke(factory);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException(e);
        }
    }
}
