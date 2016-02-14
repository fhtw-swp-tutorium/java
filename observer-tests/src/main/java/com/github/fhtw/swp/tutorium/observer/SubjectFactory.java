package com.github.fhtw.swp.tutorium.observer;

import org.reflections.ReflectionUtils;

import java.lang.reflect.Method;

import static com.github.fhtw.swp.tutorium.common.ReflectionUtils.invoke;
import static com.github.fhtw.swp.tutorium.common.ReflectionUtils.newInstance;
import static java.lang.reflect.Modifier.PUBLIC;
import static org.reflections.ReflectionUtils.*;

@SuppressWarnings("unchecked")
public class SubjectFactory {

    public static Object create(Class<?> subjectClass) {
        if (shouldUseFactory(subjectClass)) {
            final Method factoryMethod = getFactoryMethod(subjectClass);
            final Object factory = newInstance(getFactoryClass(subjectClass));

            return invoke(factoryMethod, factory);
        } else {
            return newInstance(subjectClass);
        }
    }

    private static boolean shouldUseFactory(Class<?> subjectClass) {

        final Class<?> factoryClass = getFactoryClass(subjectClass);

        return factoryClass != Subject.None.class;
    }

    private static Method getFactoryMethod(Class<?> subjectClass) {

        Class<?> factoryClass = getFactoryClass(subjectClass);

        return ReflectionUtils
                .getAllMethods(factoryClass, withParametersCount(0), withReturnType(subjectClass), withModifier(PUBLIC))
                .stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No suitable method found on given factory class for subject"));
    }

    private static Class<?> getFactoryClass(Class<?> subjectClass) {
        return subjectClass.getAnnotation(Subject.class).factory();
    }
}
