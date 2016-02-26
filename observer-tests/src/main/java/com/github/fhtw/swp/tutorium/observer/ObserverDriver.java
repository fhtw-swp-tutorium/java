package com.github.fhtw.swp.tutorium.observer;

import com.github.fhtw.swp.tutorium.common.ConfigurationFactory;
import com.github.fhtw.swp.tutorium.reflection.ClassInstanceFactory;
import com.github.fhtw.swp.tutorium.reflection.ProxyFactory;
import com.github.fhtw.swp.tutorium.reflection.impl.AnnotatedClassInstanceFactory;
import com.github.fhtw.swp.tutorium.reflection.impl.ByteBuddyProxyFactory;
import org.reflections.Configuration;
import org.reflections.Reflections;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
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

    public ProxyFactory getObserverFactory() {
        return new ByteBuddyProxyFactory();
    }

    @Deprecated
    public Object getObserverInstance(Class<?> subjectClass, InvocationHandler invocationHandler) {

        final Method registerMethod = MethodPrefixes.REGISTER.getMethodOn(subjectClass);
        final Class<?> observerClass = registerMethod.getParameterTypes()[0];

        return getObserverFactory().create(observerClass, invocationHandler);
    }

    public ClassInstanceFactory getSubjectFactory() {
        return new AnnotatedClassInstanceFactory<>(Subject.class, Subject::factory, Subject.None.class);
    }

    @Deprecated
    public Object getSubjectInstance(Class<?> subjectClass) {
        return getSubjectFactory().create(subjectClass);
    }

}
