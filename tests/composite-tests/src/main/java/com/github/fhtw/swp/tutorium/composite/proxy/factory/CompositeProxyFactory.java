package com.github.fhtw.swp.tutorium.composite.proxy.factory;

import com.github.fhtw.swp.tutorium.ArgumentsProvider;
import com.github.fhtw.swp.tutorium.composite.AddComponent;
import com.github.fhtw.swp.tutorium.composite.ComponentOperation;
import com.github.fhtw.swp.tutorium.composite.Composite;
import com.github.fhtw.swp.tutorium.composite.proxy.CompositeProxy;
import com.github.fhtw.swp.tutorium.reflection.ClassInstanceFactory;
import com.github.fhtw.swp.tutorium.reflection.SingleAnnotatedMethodExtractor;
import com.github.fhtw.swp.tutorium.reflection.impl.AnnotatedClassInstanceFactory;
import com.github.fhtw.swp.tutorium.reflection.impl.SimpleClassInstanceFactory;

import java.lang.reflect.Method;

public class CompositeProxyFactory {

    private final ClassInstanceFactory compositeFactory;
    private final ClassInstanceFactory simpleClassInstanceFactory;

    public CompositeProxyFactory() {
        this.compositeFactory = new AnnotatedClassInstanceFactory<>(Composite.class, Composite::factory);
        this.simpleClassInstanceFactory = new SimpleClassInstanceFactory();
    }

    public CompositeProxy create(Class<?> compositeType) {

        final Object composite = compositeFactory.create(compositeType);

        final SingleAnnotatedMethodExtractor extractor = new SingleAnnotatedMethodExtractor(compositeType);

        final Method addComponentMethod = extractor.getSingleAnnotatedMethod(AddComponent.class);
        final Method componentOperation = extractor.getSingleAnnotatedMethod(ComponentOperation.class);

        final Class<? extends ArgumentsProvider> argumentsProviderType = componentOperation.getAnnotation(ComponentOperation.class).argumentsProvider();

        // TODO remove this cast by making ClassInstanceFactory typed
        final ArgumentsProvider argumentsProvider = (ArgumentsProvider) simpleClassInstanceFactory.create(argumentsProviderType);

        return new CompositeProxy(composite, addComponentMethod, componentOperation, argumentsProvider);
    }
}
