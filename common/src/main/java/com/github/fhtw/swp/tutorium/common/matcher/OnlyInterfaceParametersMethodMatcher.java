package com.github.fhtw.swp.tutorium.common.matcher;

import com.google.common.collect.FluentIterable;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import java.lang.reflect.Method;
import java.util.Arrays;

public class OnlyInterfaceParametersMethodMatcher extends TypeSafeDiagnosingMatcher<Method> {

    public static Matcher<Method> onlyInterfaceParameters() {
        return new OnlyInterfaceParametersMethodMatcher();
    }

    @Override
    protected boolean matchesSafely(Method item, Description mismatchDescription) {
        return FluentIterable.from(Arrays.asList(item.getParameterTypes())).allMatch(Class::isInterface);
    }

    @Override
    public void describeTo(Description description) {

    }
}
