package org.hamcrest.reflection;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import java.lang.reflect.Method;

public class ParameterCountMethodMatcher extends TypeSafeDiagnosingMatcher<Method> {

    private final int parameterCount;

    public static Matcher<Method> parameterCount(int count) {
        return new ParameterCountMethodMatcher(count);
    }

    public ParameterCountMethodMatcher(int parameterCount) {
        this.parameterCount = parameterCount;
    }

    @Override
    protected boolean matchesSafely(Method item, Description mismatchDescription) {

        final int actualParameterCount = item.getParameterTypes().length;

        mismatchDescription.appendValue(item).appendText(" has ").appendValue(actualParameterCount).appendText(" parameters");

        return actualParameterCount == parameterCount;
    }

    @Override
    public void describeTo(Description description) {
        description.appendValue(parameterCount).appendText(" parameters");
    }
}
