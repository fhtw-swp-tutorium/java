package com.github.fhtw.swp.tutorium;

import javax.inject.Inject;

public class SwpTestTool {

    private final ExerciseTestRunner exerciseTestRunner;

    @Inject
    public SwpTestTool(ExerciseTestRunner exerciseTestRunner) {
        this.exerciseTestRunner = exerciseTestRunner;
    }

    public void testExercise(Exercise exercise) {
        exerciseTestRunner.runTests(exercise.getTestClasses());
    }

    public void testPattern(Pattern pattern) {
        exerciseTestRunner.runTests(pattern.getTestClass());
    }
}
