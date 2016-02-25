package com.github.fhtw.swp.tutorium.command;

import com.github.fhtw.swp.tutorium.common.ConfigurationFactory;
import com.github.fhtw.swp.tutorium.common.matcher.AnnotatedElementExistsMatcher;
import com.github.fhtw.swp.tutorium.common.matcher.ImplementationExistsMatcher;
import com.github.fhtw.swp.tutorium.invoker.Invoker;
import com.google.common.collect.Sets;
import org.hamcrest.Matcher;
import org.reflections.Configuration;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import static org.reflections.ReflectionUtils.withAnnotation;

@SuppressWarnings("unchecked")
public class CommandDriver {

    private Set<Class<?>> invokers;
    private Configuration configuration;

    public Set<Class<?>> getInvokers() {

        if (invokers == null) {
            configuration = new ConfigurationFactory().create();
            final Reflections reflections = new Reflections(configuration);
            invokers = reflections.getTypesAnnotatedWith(Invoker.class);
        }

        return invokers;
    }

    public Matcher<Collection<Class<?>>> getSizeMatcher() {
        return new AnnotatedElementExistsMatcher(Invoker.class);
    }

    public Set<Method> findMethodsAnnotatedWith(Class<? extends Annotation> annotation) {

        Set<Method> invokeCommandMethods = Sets.newHashSet();

        for (Class<?> invoker : invokers) {
            final Set<Method> annotatedMethods = ReflectionUtils.getAllMethods(invoker, withAnnotation(annotation));
            final Iterator<Method> methodIterator = annotatedMethods.iterator();

            if (methodIterator.hasNext()) {
                invokeCommandMethods.add(methodIterator.next());
            }
        }

        return invokeCommandMethods;
    }

    public Matcher<Class<?>> getImplementationExistsMatcher() {
        return new ImplementationExistsMatcher(configuration);
    }

    public ClassFactory getInvokerFactory() {
        return new FactoryAwareClassInstanceFactory(this::shouldUseFactory, (c) -> c.getAnnotation(Invoker.class).factory());
    }

    private boolean shouldUseFactory(Class<?> invokerClass) {
        return invokerClass.getAnnotation(Invoker.class).factory() != Invoker.None.class;
    }
}
