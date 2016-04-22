package com.github.fhtw.swp.tutorium.singleton.matcher;

import com.github.fhtw.swp.tutorium.singleton.Singletons;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Set;

@SuppressWarnings("unchecked")
public class MethodSingletonMatcher extends TypeSafeDiagnosingMatcher<Class<?>> {

    public static MethodSingletonMatcher isMethodSingleton() {
        return new MethodSingletonMatcher();
    }

    @Override
    protected boolean matchesSafely(Class<?> singletonClass, Description mismatchDescription) {

        final Set<Method> accessorMethods = ReflectionUtils.getAllMethods(singletonClass,
                Singletons.METHOD.getPredicateFor(singletonClass)
        );

        mismatchDescription.appendText("no methods in ").appendValue(singletonClass).appendText(" qualify for accessing the instance of a singleton");

        return !accessorMethods.isEmpty();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a singleton that uses a method for accessing the instance");
    }
}
