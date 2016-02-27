package com.github.fhtw.swp.tutorium.command.factory;

import com.github.fhtw.swp.tutorium.command.InvokeCommand;
import com.github.fhtw.swp.tutorium.command.Invoker;
import com.github.fhtw.swp.tutorium.command.InvokerProxy;
import com.github.fhtw.swp.tutorium.reflection.ClassInstanceFactory;
import com.github.fhtw.swp.tutorium.reflection.SingleAnnotatedMethodExtractor;
import com.github.fhtw.swp.tutorium.reflection.impl.AnnotatedClassInstanceFactory;

import java.lang.reflect.Method;

public class InvokerProxyFactory {

    private final ClassInstanceFactory invokerFactory;

    public InvokerProxyFactory() {
        this.invokerFactory = new AnnotatedClassInstanceFactory<>(Invoker.class, Invoker::factory, Invoker.None.class);
    }

    public InvokerProxy create(Class<?> invokerType) {

        final SingleAnnotatedMethodExtractor extractor = new SingleAnnotatedMethodExtractor(invokerType);

        final Object invokerInstance = invokerFactory.create(invokerType);
        final Method invokeCommandMethod = extractor.getSingleAnnotatedMethod(InvokeCommand.class);

        return new InvokerProxy(invokerInstance, invokeCommandMethod);
    }

}
