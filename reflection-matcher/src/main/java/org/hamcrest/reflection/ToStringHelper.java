package org.hamcrest.reflection;

import java.lang.reflect.Method;

public final class ToStringHelper {

    private ToStringHelper() { }

    public static String toString(Method method) {
        return String.format("%s#%s", method.getDeclaringClass().getSimpleName(), method.getName());
    }
}
