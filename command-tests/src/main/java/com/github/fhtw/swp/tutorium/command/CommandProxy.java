package com.github.fhtw.swp.tutorium.command;

import com.github.fhtw.swp.tutorium.reflection.CountingInvocationHandler;
import com.github.fhtw.swp.tutorium.reflection.impl.ByteBuddyProxyFactory;

public class CommandProxy {

    private final Object commandInstance;
    private final CountingInvocationHandler invocationHandler;

    private CommandProxy(Object commandInstance, CountingInvocationHandler invocationHandler) {
        this.commandInstance = commandInstance;
        this.invocationHandler = invocationHandler;
    }

    public static CommandProxy create(Class<?> interfaceToProxy) {

        final CountingInvocationHandler countingInvocationHandler = new CountingInvocationHandler();
        final Object instance = new ByteBuddyProxyFactory().create(interfaceToProxy, countingInvocationHandler);

        return new CommandProxy(instance, countingInvocationHandler);
    }

    public Object getCommandInstance() {
        return commandInstance;
    }

    public CountingInvocationHandler getInvocationHandler() {
        return invocationHandler;
    }
}
