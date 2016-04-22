package com.github.fhtw.swp.tutorium.singleton.accessor;

class DummySingletonProxy implements SingletonProxy {

    @Override
    public Object getInstance() {
        return new Object();
    }
}
