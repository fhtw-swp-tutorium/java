package com.github.fhtw.swp.tutorium.singleton.accessor;

import com.github.fhtw.swp.tutorium.singleton.Singletons;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Optional;

import static jdk.util.Optionals.any;


public class SingletonProxyFactory {

    public SingletonProxy create(Class<?> singletonClass) {

        final Optional<SingletonProxy> fieldSingletonAccessor = getInstanceField(singletonClass).map(FieldSingletonProxy::new);
        final Optional<SingletonProxy> methodSingletonAccessor = getInstanceMethod(singletonClass).map(MethodSingletonProxy::new);

        return any(fieldSingletonAccessor, methodSingletonAccessor, Optional.of(new DummySingletonProxy()));
    }

    @SuppressWarnings("unchecked")
    private Optional<Field> getInstanceField(Class<?> singletonClass) {
        return ReflectionUtils
                .getAllFields(singletonClass, Singletons.FIELD.getPredicateFor(singletonClass))
                .stream()
                .findFirst();
    }

    @SuppressWarnings("unchecked")
    private Optional<Method> getInstanceMethod(Class<?> singletonClass) {
        return ReflectionUtils
                .getAllMethods(singletonClass, Singletons.METHOD.getPredicateFor(singletonClass))
                .stream()
                .findFirst();
    }
}
