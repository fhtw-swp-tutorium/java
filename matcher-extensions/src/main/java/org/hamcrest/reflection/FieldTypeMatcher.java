package org.hamcrest.reflection;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import java.lang.reflect.Field;

public class FieldTypeMatcher extends TypeSafeDiagnosingMatcher<Field> {

    private final Class<?> expectedType;

    public FieldTypeMatcher(Class<?> expectedType) {
        this.expectedType = expectedType;
    }

    @Override
    protected boolean matchesSafely(Field field, Description mismatchDescription) {

        final Class<?> actualType = field.getType();

        mismatchDescription
                .appendText(ToStringHelper.toString(field))
                .appendText(" is of type ")
                .appendValue(actualType);

        return expectedType.equals(actualType);
    }

    @Override
    public void describeTo(Description description) {
        description
                .appendText("a field of type")
                .appendValue(expectedType);
    }
}
