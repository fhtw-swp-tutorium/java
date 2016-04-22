package com.github.fhtw.swp.tutorium.singleton;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import static java.lang.reflect.Modifier.PUBLIC;
import static java.lang.reflect.Modifier.STATIC;
import static org.reflections.ReflectionUtils.*;

@SuppressWarnings("unchecked")
public enum Singletons {

    FIELD(singletonClass -> Predicates.and(
            withType(singletonClass),
            withModifier(PUBLIC),
            withModifier(STATIC)
    )),
    METHOD(singletonClass -> Predicates.and(
            withReturnType(singletonClass),
            withModifier(PUBLIC),
            withModifier(STATIC),
            withParametersCount(0)
    ));

    private final Function<Class<?>, Predicate> predicateFactory;

    Singletons(Function<Class<?>, Predicate> predicateFactory) {
        this.predicateFactory = predicateFactory;
    }

    public Predicate getPredicateFor(Class<?> singletonClass) {
        return predicateFactory.apply(singletonClass);
    }
}
