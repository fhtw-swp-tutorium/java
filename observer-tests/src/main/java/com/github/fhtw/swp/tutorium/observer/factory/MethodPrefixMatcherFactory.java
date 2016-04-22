package com.github.fhtw.swp.tutorium.observer.factory;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.reflection.MethodNamePrefixMatcher;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class MethodPrefixMatcherFactory {

    public Matcher<Method> create(String delimitedPrefixes) {

        final Set<String> prefixes = Arrays
                .stream(splitByComma(delimitedPrefixes))
                .map(String::trim)
                .collect(toSet());

        final Matcher<Method> matcher = prefixes
                .stream()
                .map(MethodNamePrefixMatcher::startsWith)
                .reduce(new AlwaysFalseMatcher<>(), Matchers::anyOf);

        return matcher;
    }

    private String[] splitByComma(String delimitedPrefixes) {
        return delimitedPrefixes.split(",");
    }

    private class AlwaysFalseMatcher<T> extends BaseMatcher<T> {

        @Override
        public boolean matches(Object item) {
            return false;
        }

        @Override
        public void describeTo(Description description) {

        }
    }
}
