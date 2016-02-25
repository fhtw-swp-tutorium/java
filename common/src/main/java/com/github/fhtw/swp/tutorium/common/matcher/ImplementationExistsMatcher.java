package com.github.fhtw.swp.tutorium.common.matcher;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.reflections.Configuration;
import org.reflections.Reflections;

import java.util.Set;

public class ImplementationExistsMatcher extends TypeSafeDiagnosingMatcher<Class<?>> {

    private final Reflections reflections;

    public ImplementationExistsMatcher(Configuration configuration) {
        this.reflections = new Reflections(configuration);
    }

    @Override
    protected boolean matchesSafely(Class type, Description mismatchDescription) {

        final Set<Class> subType = reflections.getSubTypesOf(type);

        return subType.size() > 0;
    }

    @Override
    public void describeTo(Description description) {

    }
}
