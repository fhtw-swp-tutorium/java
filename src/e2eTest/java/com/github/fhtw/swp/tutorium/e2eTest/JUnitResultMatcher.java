package com.github.fhtw.swp.tutorium.e2eTest;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

public class JUnitResultMatcher {

    public static Matcher<JUnitResult> numberOfTests(Matcher<Integer> countMatcher) {
        return new FeatureMatcher<JUnitResult, Integer>(countMatcher, "JUnitResult with number of tests", "number of tests") {
            @Override
            protected Integer featureValueOf(JUnitResult actual) {
                return actual.getTotalTestCount();
            }
        };
    }

    public static Matcher<JUnitResult> numberOfFailedTests(Matcher<Integer> countMatcher) {
        return new FeatureMatcher<JUnitResult, Integer>(countMatcher, "JUnitResult with number of failed tests", "number of failed tests") {
            @Override
            protected Integer featureValueOf(JUnitResult actual) {
                return actual.getFailureCount();
            }
        };
    }

    public static Matcher<JUnitResult> numberOfSkippedTests(Matcher<Integer> countMatcher) {
        return new FeatureMatcher<JUnitResult, Integer>(countMatcher, "JUnitResult with number of skipped tests", "number of skipped tests") {
            @Override
            protected Integer featureValueOf(JUnitResult actual) {
                return actual.getSkippedCount();
            }
        };
    }
}
