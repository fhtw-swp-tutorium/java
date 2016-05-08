package org.hamcrest.reflection;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.reflections.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Set;

import static org.reflections.ReflectionUtils.withAnnotation;

public class AnnotatedFieldMatcher extends TypeSafeDiagnosingMatcher<Class<?>> {

    private final Class<? extends Annotation> expectedAnnotation;
    private final int count;

    public AnnotatedFieldMatcher(Class<? extends Annotation> expectedAnnotation, int count) {
        this.expectedAnnotation = expectedAnnotation;
        this.count = count;
    }

    public static Matcher<Class<?>> hasSingleFieldWithAnnotation(Class<? extends Annotation> expectedAnnotation) {
        return new AnnotatedFieldMatcher(expectedAnnotation, 1);
    }

    @Override
    protected boolean matchesSafely(Class<?> item, Description mismatchDescription) {

        final Set<Field> allAnnotatedFields = ReflectionUtils.getAllFields(item, withAnnotation(expectedAnnotation));

        // but
        mismatchDescription
                .appendValue(item)
                .appendText(" has ")
                .appendValue(allAnnotatedFields.size())
                .appendText(" fields annotated with ")
                .appendValue(expectedAnnotation);

        return allAnnotatedFields.size() == count;
    }

    @Override
    public void describeTo(Description description) {
        description
                .appendValue(count)
                .appendText(" fields that are annotated with ")
                .appendValue(expectedAnnotation);
    }
}
