package com.github.fhtw.swp.tutorium.shared;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class SingleTypeContext {

    private final Class<?> typeUnderTest;
    private Method methodUnderTest;

    private Class<? extends Annotation> annotation;

    public SingleTypeContext(Class<?> typeUnderTest) {
        this.typeUnderTest = typeUnderTest;
    }

    public Class<?> getTypeUnderTest() {
        return typeUnderTest;
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
