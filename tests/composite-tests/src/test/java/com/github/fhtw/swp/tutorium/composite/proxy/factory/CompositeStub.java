package com.github.fhtw.swp.tutorium.composite.proxy.factory;

import com.github.fhtw.swp.tutorium.composite.AddComponent;
import com.github.fhtw.swp.tutorium.composite.ComponentOperation;
import com.github.fhtw.swp.tutorium.composite.Composite;

@Composite(value = ComponentStub.class)
public class CompositeStub {

    @AddComponent
    public void addComponent(ComponentStub componentStub) {

    }

    @ComponentOperation(argumentsProvider = DummyArgumentProvider.class)
    public void componentOperation(Object dummyArgument) {

    }
}
