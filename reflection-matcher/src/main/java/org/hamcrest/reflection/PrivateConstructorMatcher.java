package org.hamcrest.reflection;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@SuppressWarnings("unchecked")
public class PrivateConstructorMatcher extends TypeSafeDiagnosingMatcher<Class<?>> {

    @Override
    protected boolean matchesSafely(Class<?> item, Description mismatchDescription) {

        final List<Constructor> nonPrivateConstructors = Arrays.stream(item.getDeclaredConstructors()).filter(this::isNotPrivate).collect(toList());

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
