package org.hamcrest.reflection;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class ParameterTypeMatcher extends TypeSafeDiagnosingMatcher<Method> {

    private final List<Class<?>> expectedParameterTypes;

    public ParameterTypeMatcher(List<Class<?>> expectedParameterTypes) {
        this.expectedParameterTypes = expectedParameterTypes;
    }

    public static Matcher<Method> hasParameterTypes(Class<?>... types) {
        return new ParameterTypeMatcher(Arrays.asList(types));
    }

    @Override
    protected boolean matchesSafely(Method item, Description mismatchDescription) {

        final List<? extends Class<?>> actualParameterTypes = Arrays.asList(item.getParameterTypes());

        mismatchDescription
                .appendValue(ToStringHelper.toString(item))
                .appendText(" has the following parameters")
                .appendValueList("(", ",", ")", actualParameterTypes);

        return expectedParameterTypes.equals(actualParameterTypes);
    }

    @Override
    public void describeTo(Description description) {
        description
                .appendText("a method with the following parameter types")
                .appendValueList("(", ",", ")", expectedParameterTypes);
    }
}
