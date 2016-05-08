package com.github.fhtw.swp.tutorium.composite.proxy.factory;

import com.github.fhtw.swp.tutorium.composite.Composite;
import com.github.fhtw.swp.tutorium.reflection.CountingInvocationHandler;
import com.github.fhtw.swp.tutorium.reflection.GenericInvocationCountingProxy;
import com.github.fhtw.swp.tutorium.reflection.impl.ByteBuddyProxyFactory;

public class ComponentFactory {

    public GenericInvocationCountingProxy create(Class<?> compositeClass) {

        final Class<?> componentType = compositeClass.getAnnotation(Composite.class).value();
        final CountingInvocationHandler invocationHandler = new CountingInvocationHandler();

        final Object componentInstance = new ByteBuddyProxyFactory().create(componentType, invocationHandler);

        return new GenericInvocationCountingProxy(componentInstance, invocationHandler);
    }
}
