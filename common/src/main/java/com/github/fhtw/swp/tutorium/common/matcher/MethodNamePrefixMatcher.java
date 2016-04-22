package com.github.fhtw.swp.tutorium.common.matcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import java.lang.reflect.Method;

public class MethodNamePrefixMatcher extends TypeSafeDiagnosingMatcher<Method> {

    private final String prefix;

    public MethodNamePrefixMatcher(String prefix) {
        this.prefix = prefix;
    }

    public static Matcher<Method> startsWith(String prefix) {
        return new MethodNamePrefixMatcher(prefix);
    }

    @Override
    protected boolean matchesSafely(Method method, Description mismatchDescription) {

        mismatchDescription.appendValue(method).appendText(" is named ").appendText(method.getName());

        return method.getName().startsWith(prefix);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a method that starts with ").appendValue(prefix);
    }
}
