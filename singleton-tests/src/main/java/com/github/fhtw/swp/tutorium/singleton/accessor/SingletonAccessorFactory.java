package com.github.fhtw.swp.tutorium.singleton.accessor;

import org.reflections.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

import static java.lang.reflect.Modifier.PUBLIC;
import static java.lang.reflect.Modifier.STATIC;
import static org.reflections.ReflectionUtils.*;

public class SingletonAccessorFactory {

    public SingletonAccessor create(Class<?> singletonClass) {

        final Set<Field> instanceFields = getInstanceFields(singletonClass);
        final Set<Method> instanceMethods = getInstanceMethods(singletonClass);

        if (!instanceFields.isEmpty()) {
            return new FieldSingletonAccessor(singletonClass, instanceFields.iterator().next());
        } else if (!instanceMethods.isEmpty()) {
            return new MethodSingletonAccessor(singletonClass, instanceMethods.iterator().next());
        } else {
            return new DummySingletonAccessor();
        }
    }

    @SuppressWarnings("unchecked")
    private Set<Field> getInstanceFields(Class<?> singletonClass) {
        return ReflectionUtils.getAllFields(singletonClass,
                withType(singletonClass),
                withModifier(PUBLIC),
                withModifier(STATIC)
        );
    }

    @SuppressWarnings("unchecked")
    private Set<Method> getInstanceMethods(Class<?> singletonClass) {
        return ReflectionUtils.getAllMethods(singletonClass,
                withReturnType(singletonClass),
                withModifier(PUBLIC),
                withModifier(STATIC),
                withParametersCount(0)
        );
    }
}
