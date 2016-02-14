package com.github.fhtw.swp.tutorium.singleton.accessor;

import com.github.fhtw.swp.tutorium.singleton.SingletonPredicates;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

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
                SingletonPredicates.FIELD.getPredicateFactory().apply(singletonClass)
        );
    }

    @SuppressWarnings("unchecked")
    private Set<Method> getInstanceMethods(Class<?> singletonClass) {
        return ReflectionUtils.getAllMethods(singletonClass,
                SingletonPredicates.METHOD.getPredicateFactory().apply(singletonClass)
        );
    }
}
