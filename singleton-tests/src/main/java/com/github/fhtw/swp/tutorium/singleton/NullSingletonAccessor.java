package com.github.fhtw.swp.tutorium.singleton;

class NullSingletonAccessor implements SingletonAccessor {

    @Override
    public Object getInstance() {
        throw new IllegalStateException("NullSingletonAccessor cannot retrieve instance");
    }

    @Override
    public boolean hasSingletonAccessor() {
        return false;
    }
}
