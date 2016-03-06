package com.github.fhtw.swp.tutorium;

import org.junit.runner.JUnitCore;

public class ExerciseTestRunner {

    public void runExerciseTests(Exercise exercise) {

        final Class<?>[] unitTestClasses = exercise.getUnitTestClasses();

        for (Class<?> unitTestClass : unitTestClasses) {

            System.out.printf("Testing %s pattern:\n", getPatternName(unitTestClass));

            JUnitCore.runClasses(unitTestClass);
        }
    }

    private String getPatternName(Class<?> unitTestClass) {
        final String className = unitTestClass.getSimpleName();
        return className.substring(0, className.length() - 4);
    }
}
