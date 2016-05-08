package com.github.fhtw.swp.tutorium.reflection;

import com.google.common.base.Predicate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reflections.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Set;

import static org.reflections.ReflectionUtils.withAnnotation;

// TODO remove duplicate between this and MethodExtractor
public class SingleAnnotatedFieldExtractor {

    private static final Logger LOGGER = LogManager.getLogger(SingleAnnotatedFieldExtractor.class);

    private final Class<?> typeToInspect;

    public SingleAnnotatedFieldExtractor(Class<?> typeToInspect) {
        this.typeToInspect = typeToInspect;
    }

    public Field getSingleAnnotatedField(Class<? extends Annotation> annotationToLookFor) {
        Predicate<Field> filterByAnnotation = withAnnotation(annotationToLookFor);
        final Set<Field> allAnnotatedFields = ReflectionUtils.getAllFields(typeToInspect, filterByAnnotation);

        LOGGER.info("Found {} fields annotated with {} in {}", allAnnotatedFields.size(), annotationToLookFor, typeToInspect);

        final Iterator<Field> fieldIterator = allAnnotatedFields.iterator();

        if (!fieldIterator.hasNext()) {

            final String errorMessage = "No field in %s is annotated with %s";
            throw new IllegalArgumentException(String.format(errorMessage, typeToInspect, annotationToLookFor));
        }

        return fieldIterator.next();
    }
}
