package com.github.fhtw.swp.tutorium.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class ReflectionUtils {

    private ReflectionUtils() { }

    public static Object newInstance(Class<?> subClass) {
        try {
            return subClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    public static Object invoke(Method method, Object instance, Object... arguments) {
        try {
            return method.invoke(instance, arguments);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException(e);
        }
    }
}
