package com.github.fhtw.swp.tutorium.singleton.accessor;

class DummySingletonAccessor implements SingletonAccessor {

    @Override
    public Object getInstance() {
        return new Object();
    }
}
