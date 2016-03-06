package com.github.fhtw.swp.tutorium;

import org.junit.runner.JUnitCore;

public class ExerciseTestRunner {

    public void runExerciseTests(Exercise exercise) {

        for (Class<?> testClass : exercise.getTestClasses()) {

            System.out.printf("\n\nTesting %s pattern:\n", getPatternName(testClass));

            JUnitCore.runClasses(testClass);
        }
    }

    private String getPatternName(Class<?> unitTestClass) {
        final String className = unitTestClass.getSimpleName();
        return className.substring(0, className.length() - 4);
    }
}
