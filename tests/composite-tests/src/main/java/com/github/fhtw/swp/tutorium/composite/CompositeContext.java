package com.github.fhtw.swp.tutorium.composite;

import com.github.fhtw.swp.tutorium.composite.proxy.CompositeProxy;

public class CompositeContext {

    private CompositeProxy compositeProxy;

    public CompositeProxy getCompositeProxy() {
        return compositeProxy;
    }

    public void setCompositeProxy(CompositeProxy compositeProxy) {
        this.compositeProxy = compositeProxy;
    }
}
