package com.github.fhtw.swp.tutorium.observer.factory;

import com.github.fhtw.swp.tutorium.observer.ObserverProxy;
import com.github.fhtw.swp.tutorium.observer.RegisterObserver;
import com.github.fhtw.swp.tutorium.reflection.SingleAnnotatedMethodExtractor;

public class ObserverProxyFactory {

    public ObserverProxy create(Class<?> subjectType) {

        final SingleAnnotatedMethodExtractor extractor = new SingleAnnotatedMethodExtractor(subjectType);
        final Class<?> observerClass = extractor.getFirstParameterOfSingleAnnotatedMethod(RegisterObserver.class);

        return ObserverProxy.create(observerClass);
    }
}
