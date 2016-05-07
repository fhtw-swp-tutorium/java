package org.hamcrest.reflection;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.reflections.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

import static org.reflections.ReflectionUtils.withAnnotation;

public class AnnotatedMethodMatcher extends TypeSafeDiagnosingMatcher<Class<?>> {

    private final Class<? extends Annotation> expectedAnnotation;
    private final int count;

    public AnnotatedMethodMatcher(Class<? extends Annotation> expectedAnnotation, int count) {
        this.expectedAnnotation = expectedAnnotation;
        this.count = count;
    }

    public static Matcher<Class<?>> hasSingleMethodWithAnnotation(Class<? extends Annotation> annotationType) {
        return new AnnotatedMethodMatcher(annotationType, 1);
    }

    @Override
    protected boolean matchesSafely(Class<?> item, Description mismatchDescription) {

        final Set<Method> allAnnotatedMethods = ReflectionUtils.getAllMethods(item, withAnnotation(expectedAnnotation));

        // but
        mismatchDescription
                .appendValue(item)
                .appendText(" has ")
                .appendValue(allAnnotatedMethods.size())
                .appendText(" methods annotated with ")
                .appendValue(expectedAnnotation);

        return allAnnotatedMethods.size() == count;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a single method that is annotated with ").appendValue(expectedAnnotation);
    }
}
