package com.github.fhtw.swp.tutorium.common.matcher;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class AlwaysFalseMatcher<T> extends BaseMatcher<T> {

    public static <T> Matcher<T> nothing() {
        return new AlwaysFalseMatcher<>();
    }

    @Override
    public boolean matches(Object item) {
        return false;
    }

    @Override
    public void describeTo(Description description) {

    }
}
