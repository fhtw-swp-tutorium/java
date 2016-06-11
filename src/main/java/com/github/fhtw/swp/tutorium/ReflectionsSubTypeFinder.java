package com.github.fhtw.swp.tutorium;

import org.hamcrest.reflection.SubTypeFinder;
import org.reflections.Configuration;
import org.reflections.Reflections;

import javax.inject.Inject;
import java.util.Set;

public class ReflectionsSubTypeFinder implements SubTypeFinder {

    private final Reflections reflections;

    @Inject
    public ReflectionsSubTypeFinder(Configuration configuration) {
        this.reflections = new Reflections(configuration);
    }

    @Override
    public <T> Set<Class<? extends T>> getSubTypesOf(Class<T> type) {
        return reflections.getSubTypesOf(type);
    }
}
