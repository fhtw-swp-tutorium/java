package com.github.fhtw.swp.tutorium.observer;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static com.github.fhtw.swp.tutorium.common.ReflectionUtils.newInstance;

public class ObserverFactory {

    public static Object create(Class<?> subjectClass, InvocationHandler handler) {

        final Method registerMethod = MethodPrefixes.REGISTER.getMethodOn(subjectClass);
        final Class<?> observerClass = registerMethod.getParameterTypes()[0];

        final Class<?> observerSubClass = new ByteBuddy()
                .subclass(Object.class)
                .implement(observerClass)
                .intercept(InvocationHandlerAdapter.of(handler))
                .make()
                .load(observerClass.getClassLoader(), ClassLoadingStrategy.Default.INJECTION)
                .getLoaded();

        return newInstance(observerSubClass);
    }

}
