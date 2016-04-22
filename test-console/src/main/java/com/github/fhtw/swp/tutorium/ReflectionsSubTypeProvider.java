package com.github.fhtw.swp.tutorium;

import org.hamcrest.reflection.SubTypeProvider;
import org.reflections.Configuration;
import org.reflections.Reflections;

import javax.inject.Inject;
import java.util.Set;

public class ReflectionsSubTypeProvider implements SubTypeProvider {

    private final Reflections reflections;

    @Inject
    public ReflectionsSubTypeProvider(Configuration configuration) {
        this.reflections = new Reflections(configuration);
    }

    @Override
    public <T> Set<Class<? extends T>> getSubTypesOf(Class<T> type) {
        return reflections.getSubTypesOf(type);
    }
}
