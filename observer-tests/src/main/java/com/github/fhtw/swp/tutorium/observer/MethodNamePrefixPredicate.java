package com.github.fhtw.swp.tutorium.observer;

import java.lang.reflect.Method;

import com.google.common.base.Predicate;

/**
 * @author Thomas Eizinger, Senacor Technologies AG.
 */
class MethodNamePrefixPredicate implements Predicate<Method> {

    private final String name;

    public static Predicate<Method> startsWith(String prefix) {
        return new MethodNamePrefixPredicate(prefix);
    }

    public MethodNamePrefixPredicate(String name) {
        this.name = name;
    }

    @Override
    public boolean apply(Method method) {
        return method.getName().toLowerCase().startsWith(name.toLowerCase());
    }
}
