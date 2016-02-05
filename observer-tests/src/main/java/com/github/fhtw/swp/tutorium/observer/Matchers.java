package com.github.fhtw.swp.tutorium.observer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;

public class Matchers {

    public static Matcher<Class<?>> hasMethodThatStartsWith(final String... prefixes) {

        return new TypeSafeDiagnosingMatcher<Class<?>>() {
            @Override
            protected boolean matchesSafely(Class<?> subject, Description description) {

                description.appendText("did not find such a method");

                final Set<Method> methods = new MethodExtractor().getMethodsWithAnyPrefix(subject, prefixes);

                return methods.size() > 0;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("a method that starts with any of the following prefixes: ");
                for (String prefix : Arrays.asList(prefixes)) {
                    description.appendText(prefix);
                    description.appendText(" ");
                }
            }
        };
    }

    public static Matcher<CountingInvocationHandler> wasInvoked(final int times) {

        return new TypeSafeDiagnosingMatcher<CountingInvocationHandler>() {
            @Override
            protected boolean matchesSafely(CountingInvocationHandler invocationHandler, Description description) {

                final int counter = invocationHandler.getCounter();

                description.appendText("was only invoked ");
                description.appendText(Integer.toString(counter));
                description.appendText(" times");

                return counter == times;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("invocation handler to be invoked ");
                description.appendText(Integer.toString(times));
                description.appendText(" times");
            }
        };
    }
}
