package com.github.fhtw.swp.tutorium;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class ExerciseTestRunner {

    private static final Logger LOGGER = LogManager.getLogger(ExerciseTestRunner.class);

    public void runTests(Class... tests) {
        for (Class<?> testClass : tests) {

            System.out.printf("\n\nTesting %s pattern:\n", getPatternName(testClass));

            final Result result = JUnitCore.runClasses(testClass);

            result.getFailures().forEach(f -> LOGGER.error("Failed to run test class {}", testClass, f.getException()));
        }
    }

    private String getPatternName(Class<?> unitTestClass) {
        final String className = unitTestClass.getSimpleName();
        return className.substring(0, className.length() - 4);
    }
}
