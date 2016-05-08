package com.github.fhtw.swp.tutorium.reflection;

import com.google.common.base.Predicate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reflections.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;

import static org.reflections.ReflectionUtils.withAnnotation;

public class SingleAnnotatedMethodExtractor {

    private static final Logger LOGGER = LogManager.getLogger(SingleAnnotatedMethodExtractor.class);

    private final Class<?> typeToInspect;

    public SingleAnnotatedMethodExtractor(Class<?> typeToInspect) {
        this.typeToInspect = typeToInspect;
    }

    public Method getSingleAnnotatedMethod(Class<? extends Annotation> annotationToLookFor) {
        Predicate<Method> filterByAnnotation = withAnnotation(annotationToLookFor);
        final Set<Method> allAnnotatedMethods = ReflectionUtils.getAllMethods(typeToInspect, filterByAnnotation);

        LOGGER.info("Found {} methods annotation with {} in {}", allAnnotatedMethods.size(), annotationToLookFor, typeToInspect);

        final Iterator<Method> methodIterator = allAnnotatedMethods.iterator();

        if (!methodIterator.hasNext()) {

            final String errorMessage = "No method in %s is annotated with %s";
            throw new IllegalArgumentException(String.format(errorMessage, typeToInspect, annotationToLookFor));
        }

        return methodIterator.next();
    }

    public Class<?> getFirstParameterOfSingleAnnotatedMethod(Class<? extends Annotation> annotationToLookFor) {
        return getSingleAnnotatedMethod(annotationToLookFor).getParameterTypes()[0];
    }
}
