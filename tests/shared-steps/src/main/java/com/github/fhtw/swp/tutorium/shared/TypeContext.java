package com.github.fhtw.swp.tutorium.shared;


import com.github.fhtw.swp.tutorium.reflection.AnnotatedTypeFinder;
import com.google.common.base.Predicate;
import org.reflections.Configuration;
import org.reflections.ReflectionUtils;

import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

@SuppressWarnings("unchecked")
public class TypeContext {

    private Set<Class<?>> types;
    private Map<Class<?>, Set<Method>> methodsPerType;

    private Set<Class<?>> helperTypes;
    private Map<Class<?>, Set<Method>> methodsPerHelperType;

    private final Configuration configuration;

    @Inject
    public TypeContext(Configuration configuration) {
        this.configuration = configuration;
    }

    public void initializeWithTypesAnnotatedWith(Class<? extends Annotation> annotation) {
        types = new AnnotatedTypeFinder(configuration, annotation).getAnnotatedTypes();
        methodsPerType = getMethodsOfTypes(ReflectionUtils::getAllMethods);
    }


    public void reduceMethods(Predicate<Method>... predicates) {
        methodsPerType = getMethodsOfTypes(type -> ReflectionUtils.getAllMethods(type, predicates));
    }

    public void reduceHelperTypeMethods(Predicate<Method>... predicates) {
        methodsPerHelperType = getMethodsOfTypes(type -> ReflectionUtils.getAllMethods(type, predicates));
    }

    private Map<Class<?>, Set<Method>> getMethodsOfTypes(Function<Class<?>, Set<Method>> methodsExtractor) {
        return types.stream().collect(toMap(identity(), methodsExtractor));
    }

    public void initializeHelperTypesAnnotatedWith(Class<? extends Annotation> annotation) {
        helperTypes = new AnnotatedTypeFinder(configuration, annotation).getAnnotatedTypes();
        methodsPerHelperType = getMethodsOfHelperTypes(ReflectionUtils::getAllMethods);
    }

    private Map<Class<?>, Set<Method>> getMethodsOfHelperTypes(Function<Class<?>, Set<Method>> methodsExtractor) {
        return helperTypes.stream().collect(toMap(identity(), methodsExtractor));
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public Set<Class<?>> getTypes() {
        return types;
    }

    public Set<Method> getMethodsOfType(Class<?> type) {
        return methodsPerType.getOrDefault(type, Collections.emptySet());
    }

    public Method getFirstMethodOfType(Class<?> type) {
        return getMethodsOfType(type).iterator().next();
    }

    public Set<Class<?>> getHelperTypes() {
        return helperTypes;
    }

    public Set<Method> getMethodsOfHelperType(Class<?> type) {
        return methodsPerHelperType.getOrDefault(type, Collections.emptySet());
    }

    public Method getFirstMethodOfHelperType(Class<?> type) {
        return getMethodsOfHelperType(type).iterator().next();
    }
}
