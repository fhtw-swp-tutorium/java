package com.github.fhtw.swp.tutorium.observer.factory;

import com.github.fhtw.swp.tutorium.common.matcher.MethodNamePrefixMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;

import static com.github.fhtw.swp.tutorium.common.matcher.AlwaysFalseMatcher.nothing;
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
                .reduce(nothing(), Matchers::anyOf);

        return matcher;
    }

    private String[] splitByComma(String delimitedPrefixes) {
        return delimitedPrefixes.split(",");
    }
}
