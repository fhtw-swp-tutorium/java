package com.github.fhtw.swp.tutorium.singleton.matcher;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Set;

import static org.reflections.ReflectionUtils.withModifier;
import static org.reflections.ReflectionUtils.withReturnType;

@SuppressWarnings("unchecked")
public class MethodSingletonMatcher extends TypeSafeDiagnosingMatcher<Class<?>> {

    public static MethodSingletonMatcher isMethodSingleton() {
        return new MethodSingletonMatcher();
    }

    @Override
    protected boolean matchesSafely(Class<?> item, Description mismatchDescription) {

        final Set<Method> accessorMethods = ReflectionUtils.getAllMethods(item,
                withModifier(Modifier.PUBLIC),
                withModifier(Modifier.STATIC),
                withReturnType(item)
        );

        return !accessorMethods.isEmpty();
    }

    @Override
    public void describeTo(Description description) {

    }
}
