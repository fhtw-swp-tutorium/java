package com.github.fhtw.swp.tutorium.singleton;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

/**
 * @author Thomas Eizinger, Senacor Technologies AG.
 */
public class Matchers {

    public static Matcher<Class<?>> providesSingletonAccessor() {
        return new TypeSafeDiagnosingMatcher<Class<?>>() {
            @Override
            protected boolean matchesSafely(Class<?> clazz, Description description) {

                final SingletonAccessor singletonAccessor = new SingletonAccessorFactory().create(clazz);

                description.appendText("the singleton class does neither provide a field nor a method to access the singleton instance");

                return singletonAccessor.HasSingletonAccessor();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("a field or method to access the singleton instance");
            }
        };
    }
}
