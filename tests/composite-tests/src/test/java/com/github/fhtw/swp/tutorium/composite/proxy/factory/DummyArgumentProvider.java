package com.github.fhtw.swp.tutorium.composite.proxy.factory;

import com.github.fhtw.swp.tutorium.ArgumentsProvider;

public class DummyArgumentProvider implements ArgumentsProvider {
    @Override
    public Object[] getArguments() {
        return new Object[] { new Object() };
    }
}
