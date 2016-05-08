package com.github.fhtw.swp.tutorium.decorator;

import com.github.fhtw.swp.tutorium.reflection.SingleAnnotatedFieldExtractor;

import java.lang.reflect.Field;

public class DecoratorDriver {

    public Class<?> getDefinedComponentType(Class<?> decoratorType) {
        return decoratorType.getAnnotation(Decorator.class).value();
    }

    public Field getComponentField(Class<?> decoratorType) {
        return new SingleAnnotatedFieldExtractor(decoratorType).getSingleAnnotatedField(Component.class);
    }
}
