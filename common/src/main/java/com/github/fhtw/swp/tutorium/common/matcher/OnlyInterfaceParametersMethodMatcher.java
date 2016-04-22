package com.github.fhtw.swp.tutorium.common.matcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class OnlyInterfaceParametersMethodMatcher extends TypeSafeDiagnosingMatcher<Method> {

    public static Matcher<Method> onlyInterfaceParameters() {
        return new OnlyInterfaceParametersMethodMatcher();
    }

    @Override
    protected boolean matchesSafely(Method item, Description mismatchDescription) {

        final List<Class<?>> nonInterfaceTypes = getNonInterfaceTypes(item);

        mismatchDescription.appendValueList("(", ",", ")", nonInterfaceTypes).appendText(" are not interfaces");

        return nonInterfaceTypes.isEmpty();
    }

    private List<Class<?>> getNonInterfaceTypes(Method item) {
        return Arrays.stream(item.getParameterTypes()).filter(this::isNotInterface).collect(toList());
    }

    private boolean isNotInterface(Class<?> c) {
        return !c.isInterface();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("all parameters to be interfaces");
    }
}
