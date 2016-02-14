package com.github.fhtw.swp.tutorium.singleton;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import java.lang.reflect.Modifier;

import static java.lang.reflect.Modifier.PUBLIC;
import static java.lang.reflect.Modifier.STATIC;
import static org.reflections.ReflectionUtils.*;

@SuppressWarnings("unchecked")
public enum SingletonPredicates {

    FIELD(singletonClass -> Predicates.and(
            withModifier(Modifier.PUBLIC),
            withModifier(Modifier.STATIC),
            withType(singletonClass))
    ),
    METHOD(singletonClass -> Predicates.and(
            withReturnType(singletonClass),
            withModifier(PUBLIC),
            withModifier(STATIC),
            withParametersCount(0)
    ));

    private final Function<Class<?>, Predicate> predicateFactory;

    SingletonPredicates(Function<Class<?>, Predicate> predicateFactory) {
        this.predicateFactory = predicateFactory;
    }

    public Function<Class<?>, Predicate> getPredicateFactory() {
        return predicateFactory;
    }
}
