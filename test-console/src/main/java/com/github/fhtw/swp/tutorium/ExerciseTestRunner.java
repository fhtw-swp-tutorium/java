package com.github.fhtw.swp.tutorium;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import java.util.Arrays;

public class ExerciseTestRunner {

    public int runExerciseTests(Exercise exercise) {

        final Class<?>[] unitTestClasses = exercise.getUnitTestClasses();

        return Arrays
                .stream(unitTestClasses)
                .map(JUnitCore::runClasses)
                .map(Result::getFailureCount)
                .reduce(0, this::sum);
    }

    private int sum(Integer first, Integer second) {
        return first + second;
    }
}
