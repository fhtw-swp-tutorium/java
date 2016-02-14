package com.github.fhtw.swp.tutorium.singleton.matcher;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Set;

import static org.reflections.ReflectionUtils.withModifier;
import static org.reflections.ReflectionUtils.withType;

@SuppressWarnings("unchecked")
public class FieldSingletonMatcher extends TypeSafeDiagnosingMatcher<Class<?>> {

    public static FieldSingletonMatcher isFieldSingleton() {
        return new FieldSingletonMatcher();
    }

    @Override
    protected boolean matchesSafely(Class<?> item, Description mismatchDescription) {

        final Set<Field> accessorFields = ReflectionUtils.getAllFields(item,
                withModifier(Modifier.PUBLIC),
                withModifier(Modifier.STATIC),
                withType(item)
        );

        return !accessorFields.isEmpty();
    }

    @Override
    public void describeTo(Description description) {

    }
}
