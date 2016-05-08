package com.github.fhtw.swp.tutorium.reflection;

import com.github.fhtw.swp.tutorium.reflection.impl.ByteBuddyProxyFactory;

public class GenericInvocationCountingProxy {

    private final Object instance;
    private final CountingInvocationHandler countingInvocationHandler;

    public GenericInvocationCountingProxy(Object instance, CountingInvocationHandler countingInvocationHandler) {
        this.instance = instance;
        this.countingInvocationHandler = countingInvocationHandler;
    }

    public static GenericInvocationCountingProxy create(Class<?> observerInterface) {

        final CountingInvocationHandler countingInvocationHandler = new CountingInvocationHandler();
        final Object instance = new ByteBuddyProxyFactory().create(observerInterface, countingInvocationHandler);

        return new GenericInvocationCountingProxy(instance, countingInvocationHandler);
    }

    public Object getInstance() {
        return instance;
    }

    public CountingInvocationHandler getCountingInvocationHandler() {
        return countingInvocationHandler;
    }
}
