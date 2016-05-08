package com.github.fhtw.swp.tutorium.reflection.impl;

import com.github.fhtw.swp.tutorium.reflection.ClassInstanceFactory;
import com.github.fhtw.swp.tutorium.reflection.ProxyFactory;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;

import java.lang.reflect.InvocationHandler;

public class ByteBuddyProxyFactory implements ProxyFactory {

    private ClassInstanceFactory classInstanceFactory = new SimpleClassInstanceFactory();

    @Override
    public Object create(Class<?> interfaceToProxy, InvocationHandler invocationHandler) {

        final Class<?> observerSubClass = new ByteBuddy()
                .subclass(Object.class)
                .implement(interfaceToProxy)
                .intercept(InvocationHandlerAdapter.of(invocationHandler))
                .make()
                .load(interfaceToProxy.getClassLoader(), ClassLoadingStrategy.Default.INJECTION)
                .getLoaded();

        return classInstanceFactory.create(observerSubClass);
    }
}
