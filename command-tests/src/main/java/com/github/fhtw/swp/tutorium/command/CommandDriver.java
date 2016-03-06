package com.github.fhtw.swp.tutorium.command;

import com.github.fhtw.swp.tutorium.command.factory.CommandProxyFactory;
import com.github.fhtw.swp.tutorium.command.factory.InvokerProxyFactory;
import com.github.fhtw.swp.tutorium.reflection.SingleAnnotatedMethodExtractor;
import com.google.common.collect.Maps;

import java.util.Map;

public class CommandDriver {

    private final InvokerProxyFactory invokerProxyFactory;
    private final CommandProxyFactory commandProxyFactory;
    private final Map<Class<?>, InvokerProxy> invokerProxyMap = Maps.newHashMap();
    private final Map<Class<?>, CommandProxy> commandProxyMap = Maps.newHashMap();

    public CommandDriver() {
        invokerProxyFactory = new InvokerProxyFactory();
        commandProxyFactory = new CommandProxyFactory();
    }

    public Class<?> getCommandType(Class<?> invokerType) {
        final SingleAnnotatedMethodExtractor extractor = new SingleAnnotatedMethodExtractor(invokerType);
        return extractor.getFirstParameterOfSingleAnnotatedMethod(InvokeCommand.class);
    }

    public void createInvokerProxyInstance(Class<?> invokerType) {
        final InvokerProxy invokerProxy = invokerProxyFactory.create(invokerType);
        invokerProxyMap.put(invokerType, invokerProxy);
    }

    public void createCommandProxyInstance(Class<?> invokerType) {
        final CommandProxy commandProxy = commandProxyFactory.create(invokerType);
        commandProxyMap.put(invokerType, commandProxy);
    }

    public InvokerProxy getInvokerProxyInstance(Class<?> invokerType) {
        return invokerProxyMap.get(invokerType);
    }

    public CommandProxy getCommandProxyInstance(Class<?> invokerType) {
        return commandProxyMap.get(invokerType);
    }
}
