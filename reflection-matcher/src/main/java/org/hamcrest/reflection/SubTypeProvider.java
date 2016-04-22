package org.hamcrest.reflection;

import java.util.Set;

public interface SubTypeProvider {

    <T> Set<Class<? extends T>> getSubTypesOf(Class<T> type);
}
