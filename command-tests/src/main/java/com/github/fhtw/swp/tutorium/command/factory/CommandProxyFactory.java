package com.github.fhtw.swp.tutorium.command.factory;

import com.github.fhtw.swp.tutorium.command.CommandProxy;
import com.github.fhtw.swp.tutorium.command.InvokeCommand;
import com.github.fhtw.swp.tutorium.reflection.CountingInvocationHandler;
import com.github.fhtw.swp.tutorium.reflection.ProxyFactory;
import com.github.fhtw.swp.tutorium.reflection.SingleAnnotatedMethodExtractor;
import com.github.fhtw.swp.tutorium.reflection.impl.ByteBuddyProxyFactory;

public class CommandProxyFactory {

    private ProxyFactory proxyFactory;

    public CommandProxyFactory() {
        proxyFactory = new ByteBuddyProxyFactory();
    }

    public CommandProxy create(Class<?> invokerType) {

        final SingleAnnotatedMethodExtractor extractor = new SingleAnnotatedMethodExtractor(invokerType);

        final Class<?> commandType = extractor.getFirstParameterOfSingleAnnotatedMethod(InvokeCommand.class);
        final CountingInvocationHandler countingInvocationHandler = new CountingInvocationHandler();

        final Object command = proxyFactory.create(commandType, countingInvocationHandler);

        return new CommandProxy(command, countingInvocationHandler);
    }
}
