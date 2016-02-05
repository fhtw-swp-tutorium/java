package com.github.fhtw.tutorium.swp.observer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Thomas Eizinger, Senacor Technologies AG.
 */
public class CountingInvocationHandler implements InvocationHandler {

    private int counter = 0;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        counter++;
        return null;
    }

    public int getCounter() {
        return counter;
    }
}
