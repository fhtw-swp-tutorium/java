package com.github.fhtw.swp.tutorium.common.matcher;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Set;

@SuppressWarnings("unchecked")
public class PrivateConstructorMatcher extends TypeSafeDiagnosingMatcher<Class<?>> {

    @Override
    protected boolean matchesSafely(Class<?> item, Description mismatchDescription) {

        final Set<Constructor> allConstructors = ReflectionUtils.getAllConstructors(item);

        return allConstructors.stream().allMatch(this::isPrivate);
    }

    private boolean isPrivate(Constructor ctor) {
        return Modifier.isPrivate(ctor.getModifiers());
    }

    @Override
    public void describeTo(Description description) {

    }
}
