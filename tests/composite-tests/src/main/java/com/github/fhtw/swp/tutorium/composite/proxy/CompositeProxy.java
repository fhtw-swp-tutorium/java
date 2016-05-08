package com.github.fhtw.swp.tutorium.composite.proxy;

import com.github.fhtw.swp.tutorium.ArgumentsProvider;
import com.github.fhtw.swp.tutorium.reflection.GenericInvocationCountingProxy;
import com.github.fhtw.swp.tutorium.reflection.MethodProxy;

import java.lang.reflect.Method;

public class CompositeProxy {

    private final Object composite;
    private final MethodProxy addComponentMethod;
    private final MethodProxy componentOperation;
    private final ArgumentsProvider componentOperationArgumentsProvider;

    public CompositeProxy(Object composite, Method addComponentMethod, Method componentOperation, ArgumentsProvider componentOperationArgumentsProvider) {
        this.composite = composite;
        this.addComponentMethod = new MethodProxy(addComponentMethod);
        this.componentOperation = new MethodProxy(componentOperation);
        this.componentOperationArgumentsProvider = componentOperationArgumentsProvider;
    }

    public void addComponent(GenericInvocationCountingProxy componentProxy) {

        final Object componentInstance = componentProxy.getInstance();

        addComponentMethod.invoke(composite, componentInstance);
    }

    public void invokeComponentOperation() {

        final Object[] arguments = componentOperationArgumentsProvider.getArguments();

        componentOperation.invoke(composite, arguments);
    }
}
