package com.github.fhtw.tutorium.swp.singleton;

/**
 * @author Thomas Eizinger, Senacor Technologies AG.
 */
class NullSingletonAccessor implements SingletonAccessor {

    @Override
    public Object getInstance() {
        throw new IllegalStateException("NullSingletonAccessor cannot retrieve instance");
    }

    @Override
    public boolean HasSingletonAccessor() {
        return false;
    }
}
