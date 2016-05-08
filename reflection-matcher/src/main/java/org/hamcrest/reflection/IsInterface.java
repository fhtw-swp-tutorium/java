package org.hamcrest.reflection;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public class IsInterface extends TypeSafeDiagnosingMatcher<Class> {



    @Override
    protected boolean matchesSafely(Class item, Description mismatchDescription) {

        mismatchDescription.appendValue(item).appendText(" is not an interface");

        return item.isInterface();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("type to be an interface");
    }
}
