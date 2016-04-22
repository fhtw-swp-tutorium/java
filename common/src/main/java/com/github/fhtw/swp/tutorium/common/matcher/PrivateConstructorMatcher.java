package com.github.fhtw.swp.tutorium.common.matcher;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@SuppressWarnings("unchecked")
public class PrivateConstructorMatcher extends TypeSafeDiagnosingMatcher<Class<?>> {

    @Override
    protected boolean matchesSafely(Class<?> item, Description mismatchDescription) {

        final Set<Constructor> allConstructors = ReflectionUtils.getAllConstructors(item);

        final List<Constructor> nonPrivateConstructors = allConstructors.stream().filter(this::isNotPrivate).collect(toList());

        mismatchDescription
                .appendText("constructors ")
                .appendValueList("(", ",", ")", nonPrivateConstructors)
                .appendText(" in class ")
                .appendValue(item)
                .appendText(" are not private");

        return nonPrivateConstructors.isEmpty();
    }

    private boolean isNotPrivate(Constructor ctor) {
        return !Modifier.isPrivate(ctor.getModifiers());
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("all constructors to be private");
    }
}
