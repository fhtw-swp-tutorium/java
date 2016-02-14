package com.github.fhtw.swp.tutorium.observer;

import com.github.fhtw.swp.tutorium.common.ConfigurationFactory;
import com.github.fhtw.swp.tutorium.common.matcher.AnnotatedElementExistsMatcher;
import org.hamcrest.Matcher;
import org.reflections.Configuration;
import org.reflections.Reflections;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Set;

@SuppressWarnings("unchecked")
public class ObserverDriver {

    private final Set<Class<?>> subjects;

    public ObserverDriver() {
        final Configuration configuration = new ConfigurationFactory().create();

        final Reflections reflections = new Reflections(configuration);
        subjects = reflections.getTypesAnnotatedWith(Subject.class);
    }

    public Set<Class<?>> getSubjects() {
        return subjects;
    }

    public Matcher<Collection<Class<?>>> getSizeMatcher() {
        return new AnnotatedElementExistsMatcher(Subject.class);
    }

    public Object getObserverInstance(Class<?> subjectClass, InvocationHandler invocationHandler) {
        return ObserverFactory.create(subjectClass, invocationHandler);
    }

    public Object getSubjectInstance(Class<?> subjectClass) {
        return SubjectFactory.create(subjectClass);
    }

    public Matcher<Method> getRegisterMethodMatcher() {
        return MethodPrefixes.REGISTER.getMatcher();
    }

    public Matcher<Method> getUnregisterMethodMatcher() {
        return MethodPrefixes.UNREGISTER.getMatcher();
    }

    public Matcher<Method> getUpdateMethodMatcher() {
        return MethodPrefixes.UPDATE.getMatcher();
    }
}
