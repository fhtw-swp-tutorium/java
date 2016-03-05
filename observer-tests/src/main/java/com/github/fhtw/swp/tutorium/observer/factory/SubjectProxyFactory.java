package com.github.fhtw.swp.tutorium.observer.factory;

import com.github.fhtw.swp.tutorium.observer.*;
import com.github.fhtw.swp.tutorium.reflection.ClassInstanceFactory;
import com.github.fhtw.swp.tutorium.reflection.SingleAnnotatedMethodExtractor;
import com.github.fhtw.swp.tutorium.reflection.impl.AnnotatedClassInstanceFactory;

import java.lang.reflect.Method;

public class SubjectProxyFactory {

    private final ClassInstanceFactory subjectFactory;

    public SubjectProxyFactory() {
        subjectFactory = new AnnotatedClassInstanceFactory<>(Subject.class, Subject::factory);
    }

    public SubjectProxy create(Class<?> subjectType) {

        final SingleAnnotatedMethodExtractor extractor = new SingleAnnotatedMethodExtractor(subjectType);

        final Object subjectInstance = subjectFactory.create(subjectType);
        final Method registerMethod = extractor.getSingleAnnotatedMethod(RegisterObserver.class);
        final Method unregisterMethod = extractor.getSingleAnnotatedMethod(UnregisterObserver.class);
        final Method updateMethod = extractor.getSingleAnnotatedMethod(NotifyObservers.class);

        return new SubjectProxy(subjectInstance, registerMethod, unregisterMethod, updateMethod);
    }
}
