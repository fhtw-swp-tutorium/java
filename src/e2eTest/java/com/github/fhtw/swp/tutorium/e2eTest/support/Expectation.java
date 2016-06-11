package com.github.fhtw.swp.tutorium.e2eTest.support;

public @interface Expectation {
    int numberOfTests();
    int numberOfFailedTests() default 0;
    int numberOfSkippedTests() default 0;

}
