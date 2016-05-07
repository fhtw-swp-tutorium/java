package com.github.fhtw.swp.tutorium.shared;

import org.reflections.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import static org.reflections.ReflectionUtils.withAnnotation;

public class SingleTypeContext {

    private final Class<?> typeUnderTest;
    private Method methodUnderTest;

    private Class<? extends Annotation> annotation;

    private Set<Method> methods;

    public SingleTypeContext(Class<?> typeUnderTest) {
        this.typeUnderTest = typeUnderTest;
        this.methods = new HashSet<>();
    }

    public Class<?> getTypeUnderTest() {
        return typeUnderTest;
    }

    public void findMethodsAnnotatedWith(Class<? extends Annotation> annotationType) {
        methods = ReflectionUtils.getAllMethods(typeUnderTest, withAnnotation(annotationType));
    }

    public Set<Method> getMethods() {
        return methods;
    }

    public Class<? extends Annotation> getAnnotation() {
        return annotation;
    }

    public void setAnnotation(Class<? extends Annotation> annotation) {
        this.annotation = annotation;
    }

    public Method getMethodUnderTest() {
        return methodUnderTest;
    }

    public void setMethodUnderTest(Method methodUnderTest) {
        this.methodUnderTest = methodUnderTest;
    }
}
