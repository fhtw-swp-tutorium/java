package com.github.fhtw.swp.tutorium.composite.proxy.factory;

import com.github.fhtw.swp.tutorium.reflection.CountingInvocationHandler;
import com.github.fhtw.swp.tutorium.reflection.GenericInvocationCountingProxy;
import com.github.fhtw.swp.tutorium.reflection.impl.ByteBuddyProxyFactory;

public class ComponentFactory {

    private final ByteBuddyProxyFactory proxyFactory;

    public ComponentFactory() {
        proxyFactory = new ByteBuddyProxyFactory();
    }

    public GenericInvocationCountingProxy create(Class<?> componentType) {

        final CountingInvocationHandler invocationHandler = new CountingInvocationHandler();
        final Object componentInstance = proxyFactory.create(componentType, invocationHandler);

        return new GenericInvocationCountingProxy(componentInstance, invocationHandler);
    }
}
