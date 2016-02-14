package com.github.fhtw.swp.tutorium.common.matcher;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import java.lang.annotation.Annotation;
import java.util.Collection;

public class AnnotatedElementExistsMatcher extends TypeSafeDiagnosingMatcher<Collection<Class<?>>> {

    private final Class<? extends Annotation> annotation;

    public AnnotatedElementExistsMatcher(Class<? extends Annotation> annotation) {
        this.annotation = annotation;
    }

    @Override
    protected boolean matchesSafely(Collection<Class<?>> item, Description mismatchDescription) {
        return item.size() > 0;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("at least one class annotated with @").appendValue(annotation.getSimpleName());
    }
}
