package com.github.fhtw.swp.tutorium.command;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InvokerProxy {

    private final Object invokerInstance;
    private final Method invokeMethod;

    public InvokerProxy(Object invokerInstance, Method invokeMethod) {
        this.invokerInstance = invokerInstance;
        this.invokeMethod = invokeMethod;
    }

    public void execute(CommandProxy commandProxy) {
        try {
            invokeMethod.invoke(invokerInstance, commandProxy.getCommandInstance());
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException(e);
        }
    }
}
