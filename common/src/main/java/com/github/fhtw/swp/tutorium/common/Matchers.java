package com.github.fhtw.swp.tutorium.common;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.util.Set;

import static java.lang.reflect.Modifier.PUBLIC;

public class Matchers {

    public static Matcher<Set<Class<?>>> exist() {

        return new TypeSafeDiagnosingMatcher<Set<Class<?>>>() {
            @Override
            protected boolean matchesSafely(Set<Class<?>> subjects, Description description) {

                description.appendValue("did not find any class with @Subject annotation.");

                return subjects.size() > 0;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("at least one class with @Subject annotation");
            }
        };
    }

    public static TypeSafeDiagnosingMatcher<Class<?>> isInterface() {
        return new TypeSafeDiagnosingMatcher<Class<?>>() {
            @Override
            protected boolean matchesSafely(Class<?> clazz, Description description) {

                description.appendText("class ");
                description.appendText(clazz.getSimpleName());
                description.appendText(" is not an interface");

                return clazz.isInterface();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("the observer class to be an interface");
            }
        };
    }

    public static Matcher<Class<?>> hasNoPublicConstructor() {
        return new TypeSafeDiagnosingMatcher<Class<?>>() {
            @Override
            protected boolean matchesSafely(Class<?> clazz, Description description) {

                final Set<Constructor> publicConstructors = ReflectionUtils.getAllConstructors(clazz, ReflectionUtils.withModifier(PUBLIC));

                description.appendText("found ");
                description.appendText(Integer.toString(publicConstructors.size()));
                description.appendText(" public constructors");

                return publicConstructors.isEmpty();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("no public constructor in singleton class");
            }
        };
    }
}
