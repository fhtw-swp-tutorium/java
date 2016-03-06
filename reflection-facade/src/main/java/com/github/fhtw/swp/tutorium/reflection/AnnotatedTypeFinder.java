package com.github.fhtw.swp.tutorium.reflection;

import org.reflections.Configuration;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.Set;

public class AnnotatedTypeFinder {

    private final Configuration configuration;
    private final Class<? extends Annotation> annotationType;

    private Set<Class<?>> types;

    public AnnotatedTypeFinder(Configuration configuration, Class<? extends Annotation> annotationType) {
        this.configuration = configuration;
        this.annotationType = annotationType;
    }

    public Set<Class<?>> getAnnotatedTypes() {
        if (types == null) {
            final Reflections reflections = new Reflections(configuration);
            types = reflections.getTypesAnnotatedWith(annotationType);
        }

        return types;
    }
}
