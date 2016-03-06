package com.github.fhtw.swp.tutorium.observer;

import com.github.fhtw.swp.tutorium.observer.factory.ObserverProxyFactory;
import com.github.fhtw.swp.tutorium.observer.factory.SubjectProxyFactory;
import com.google.common.collect.Maps;

import java.util.Map;

@SuppressWarnings("unchecked")
public class ObserverDriver {

    private final Map<Class<?>, SubjectProxy> subjectProxies = Maps.newHashMap();
    private final Map<Class<?>, ObserverProxy> observerProxies = Maps.newHashMap();
    private final SubjectProxyFactory subjectProxyFactory;
    private final ObserverProxyFactory observerProxyFactory;

    public ObserverDriver() {
        observerProxyFactory = new ObserverProxyFactory();
        subjectProxyFactory = new SubjectProxyFactory();
    }

    public void createSubjectProxyInstance(Class<?> subjectType) {
        final SubjectProxy subject = subjectProxyFactory.create(subjectType);
        subjectProxies.put(subjectType, subject);
    }

    public void createObserverProxyInstance(Class<?> subjectType) {
        final ObserverProxy observer = observerProxyFactory.create(subjectType);
        observerProxies.put(subjectType, observer);
    }

    public SubjectProxy getSubjectProxyInstance(Class<?> subjectType) {
        return subjectProxies.get(subjectType);
    }

    public ObserverProxy getObserverProxyInstance(Class<?> subjectType) {
        return observerProxies.get(subjectType);
    }
}
