package org.hamcrest.reflection;

import java.util.Set;

public interface SubTypeFinder {

    <T> Set<Class<? extends T>> getSubTypesOf(Class<T> type);
}
