package com.github.fhtw.swp.tutorium.composite;

import com.github.fhtw.swp.tutorium.composite.proxy.CompositeProxy;
import com.github.fhtw.swp.tutorium.reflection.GenericInvocationCountingProxy;

public class CompositeContext {

    private CompositeProxy compositeProxy;
    private GenericInvocationCountingProxy componentProxy;

    public CompositeProxy getCompositeProxy() {
        return compositeProxy;
    }

    public void setCompositeProxy(CompositeProxy compositeProxy) {
        this.compositeProxy = compositeProxy;
    }

    public GenericInvocationCountingProxy getComponentProxy() {
        return componentProxy;
    }

    public void setComponentProxy(GenericInvocationCountingProxy componentProxy) {
        this.componentProxy = componentProxy;
    }
}
