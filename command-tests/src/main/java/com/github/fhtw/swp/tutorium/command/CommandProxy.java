package com.github.fhtw.swp.tutorium.command;

import com.github.fhtw.swp.tutorium.reflection.CountingInvocationHandler;

public class CommandProxy {

    private final Object commandInstance;
    private final CountingInvocationHandler invocationHandler;

    public CommandProxy(Object commandInstance, CountingInvocationHandler invocationHandler) {
        this.commandInstance = commandInstance;
        this.invocationHandler = invocationHandler;
    }

    public Object getCommandInstance() {
        return commandInstance;
    }

    public CountingInvocationHandler getInvocationHandler() {
        return invocationHandler;
    }
}
