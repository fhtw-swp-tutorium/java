package com.github.fhtw.swp.tutorium.singleton.matcher;

import com.github.fhtw.swp.tutorium.singleton.Singletons;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Set;

@SuppressWarnings("unchecked")
public class FieldSingletonMatcher extends TypeSafeDiagnosingMatcher<Class<?>> {

    public static FieldSingletonMatcher isFieldSingleton() {
        return new FieldSingletonMatcher();
    }

    @Override
    protected boolean matchesSafely(Class<?> singletonClass, Description mismatchDescription) {

        final Set<Field> accessorFields = ReflectionUtils.getAllFields(singletonClass,
                Singletons.FIELD.getPredicateFor(singletonClass)
        );

        mismatchDescription.appendText("no fields in ").appendValue(singletonClass).appendText(" qualify for accessing the instance of a singleton");

        return !accessorFields.isEmpty();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a singleton that uses a field for accessing the instance");
    }
}
