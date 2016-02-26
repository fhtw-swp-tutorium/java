package com.github.fhtw.swp.tutorium.command;

import com.github.fhtw.swp.tutorium.common.TypeContext;
import com.github.fhtw.swp.tutorium.reflection.ClassInstanceFactory;
import com.github.fhtw.swp.tutorium.reflection.impl.AnnotatedClassInstanceFactory;
import com.google.common.collect.Maps;

import java.lang.reflect.Method;
import java.util.Map;

public class CommandDriver {

    private final ClassInstanceFactory invokerFactory;
    private final TypeContext typeContext;
    private final Map<Class<?>, InvokerProxy> invokerProxyMap = Maps.newHashMap();
    private final Map<Class<?>, CommandProxy> commandProxyMap = Maps.newHashMap();

    public CommandDriver(TypeContext typeContext) {
        this.typeContext = typeContext;
        this.invokerFactory = new AnnotatedClassInstanceFactory<>(Invoker.class, Invoker::factory, Invoker.None.class);
    }

    public Class<?> getCommandType(Class<?> invokerType) {
        return typeContext.getFirstMethodOfType(invokerType).getParameterTypes()[0];
    }

    public void createInvokerProxyInstance(Class<?> invokerType) {

        final Object invokerInstance = invokerFactory.create(invokerType);
        final Method invokeCommandMethod = typeContext.getFirstMethodOfType(invokerType);

        invokerProxyMap.put(invokerType, new InvokerProxy(invokerInstance, invokeCommandMethod));
    }

    public void createCommandProxyInstance(Class<?> invokerType) {

        final Class<?> commandInterfaceType = getCommandType(invokerType);
        final CommandProxy commandProxy = CommandProxy.create(commandInterfaceType);

        commandProxyMap.put(invokerType, commandProxy);
    }

    public InvokerProxy getInvokerProxyInstance(Class<?> invokerType) {
        return invokerProxyMap.get(invokerType);
    }

    public CommandProxy getCommandProxyInstance(Class<?> invokerType) {
        return commandProxyMap.get(invokerType);
    }
}
