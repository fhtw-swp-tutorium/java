package com.github.fhtw.swp.tutorium.singleton.accessor;

import com.github.fhtw.swp.tutorium.singleton.Singletons;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Optional;

import static com.github.fhtw.swp.tutorium.common.Optionals.either;

public class SingletonAccessorFactory {

    public SingletonAccessor create(Class<?> singletonClass) {

        final Optional<SingletonAccessor> fieldSingletonAccessor = getInstanceField(singletonClass).map(FieldSingletonAccessor::new);
        final Optional<SingletonAccessor> methodSingletonAccessor = getInstanceMethod(singletonClass).map(MethodSingletonAccessor::new);

        return either(fieldSingletonAccessor, methodSingletonAccessor).orElseGet(DummySingletonAccessor::new);
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
