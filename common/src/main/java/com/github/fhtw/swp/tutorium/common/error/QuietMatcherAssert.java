package com.github.fhtw.swp.tutorium.common.error;

import org.hamcrest.Matcher;

public class QuietMatcherAssert {
    public static <T> void assertThat(T actual, Matcher<? super T> matcher) {
        if (!matcher.matches(actual)) {
            throw new QuietAssertionError();
        }
    }
}
