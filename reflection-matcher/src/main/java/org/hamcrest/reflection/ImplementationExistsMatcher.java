package org.hamcrest.reflection;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import java.util.Set;

public class ImplementationExistsMatcher extends TypeSafeDiagnosingMatcher<Class<?>> {

    private final SubTypeProvider subTypeProvider;

    public ImplementationExistsMatcher(SubTypeProvider subTypeProvider) {
        this.subTypeProvider = subTypeProvider;
    }

    @Override
    protected boolean matchesSafely(Class type, Description mismatchDescription) {

        final Set<Class<?>> subType = subTypeProvider.getSubTypesOf(type);

        mismatchDescription.appendValue(type).appendText(" does not have any implementations");

        return subType.size() > 0;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("at least one implementation");
    }
}
