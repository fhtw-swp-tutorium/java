package com.github.fhtw.swp.tutorium.observer.factory;

import com.github.fhtw.swp.tutorium.observer.RegisterObserver;
import com.github.fhtw.swp.tutorium.reflection.GenericInvocationCountingProxy;
import com.github.fhtw.swp.tutorium.reflection.SingleAnnotatedMethodExtractor;

public class ObserverProxyFactory {

    public GenericInvocationCountingProxy create(Class<?> subjectType) {

        final SingleAnnotatedMethodExtractor extractor = new SingleAnnotatedMethodExtractor(subjectType);

        // TODO observer type should be declared in subject annotation
        final Class<?> observerClass = extractor.getFirstParameterOfSingleAnnotatedMethod(RegisterObserver.class);

        return GenericInvocationCountingProxy.create(observerClass);
    }
}
