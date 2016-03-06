package com.github.fhtw.swp.tutorium.common;

import com.github.fhtw.swp.tutorium.reflection.AnnotatedTypeFinder;
import com.google.common.base.Predicate;
import org.reflections.Configuration;
import org.reflections.ReflectionUtils;

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
    private Configuration configuration;

    public void initializeWithTypesAnnotatedWith(Class<? extends Annotation> annotation) {
        configuration = new ConfigurationFactory().create();

        types = new AnnotatedTypeFinder(configuration, annotation).getAnnotatedTypes();
        methodsPerType = getMethodsOfTypes(ReflectionUtils::getAllMethods);
    }

    public void reduceMethods(Predicate<Method>... predicates) {
        methodsPerType = getMethodsOfTypes(type -> ReflectionUtils.getAllMethods(type, predicates));
    }

    private Map<Class<?>, Set<Method>> getMethodsOfTypes(Function<Class<?>, Set<Method>> methodsExtractor) {
        return types.stream().collect(toMap(identity(), methodsExtractor));
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
}
