package org.hamcrest.reflection;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import java.util.Arrays;

public class ImplementsInterface extends TypeSafeDiagnosingMatcher<Class<?>> {

    private final Class<?> expectedInterface;

    public ImplementsInterface(Class<?> expectedInterface) {
        this.expectedInterface = expectedInterface;
    }

    @Override
    protected boolean matchesSafely(Class item, Description mismatchDescription) {

        final Class[] implementedInterfaces = item.getInterfaces();

        mismatchDescription
                .appendValue(item)
                .appendText(" only implements the following interfaces")
                .appendValueList("(", ",", ")", implementedInterfaces);

        return Arrays.stream(implementedInterfaces).anyMatch(c -> c.equals(expectedInterface));
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a type that implements").appendValue(expectedInterface);
    }
}
