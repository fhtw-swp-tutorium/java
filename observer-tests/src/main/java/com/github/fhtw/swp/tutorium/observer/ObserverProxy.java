package com.github.fhtw.swp.tutorium.observer;

import com.github.fhtw.swp.tutorium.reflection.CountingInvocationHandler;
import com.github.fhtw.swp.tutorium.reflection.impl.ByteBuddyProxyFactory;

public class ObserverProxy {

    private final Object instance;
    private final CountingInvocationHandler countingInvocationHandler;

    public ObserverProxy(Object instance, CountingInvocationHandler countingInvocationHandler) {
        this.instance = instance;
        this.countingInvocationHandler = countingInvocationHandler;
    }

    public static ObserverProxy create(Class<?> observerInterface) {

        final CountingInvocationHandler countingInvocationHandler = new CountingInvocationHandler();
        final Object instance = new ByteBuddyProxyFactory().create(observerInterface, countingInvocationHandler);

        return new ObserverProxy(instance, countingInvocationHandler);
    }

    public Object getInstance() {
        return instance;
    }

    public CountingInvocationHandler getCountingInvocationHandler() {
        return countingInvocationHandler;
    }
}
