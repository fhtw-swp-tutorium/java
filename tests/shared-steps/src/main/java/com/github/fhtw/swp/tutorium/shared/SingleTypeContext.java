package com.github.fhtw.swp.tutorium.shared;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

// TODO should we really share this context over all tests or simply create a new context class for every test?
public class SingleTypeContext {

    private final Class<?> typeUnderTest;
    private Method methodUnderTest;
    private Field fieldUnderTest;

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

    public Field getFieldUnderTest() {
        return fieldUnderTest;
    }

    public void setFieldUnderTest(Field fieldUnderTest) {
        this.fieldUnderTest = fieldUnderTest;
    }
}
