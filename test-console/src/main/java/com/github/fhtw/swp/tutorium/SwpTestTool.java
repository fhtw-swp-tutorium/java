package com.github.fhtw.swp.tutorium;

import com.github.fhtw.swp.tutorium.inject.CurrentSut;

import javax.inject.Inject;
import java.net.URL;

import static java.lang.ClassLoader.getSystemClassLoader;

public class SwpTestTool {

    private final MutableClassLoader mutableClassLoader = new MutableClassLoader(getSystemClassLoader());

    private final URL urlToSut;
    private final ExerciseTestRunner exerciseTestRunner;

    @Inject
    public SwpTestTool(@CurrentSut URL urlToSut, ExerciseTestRunner exerciseTestRunner) {
        this.urlToSut = urlToSut;
        this.exerciseTestRunner = exerciseTestRunner;
    }

    public void testExercise(Exercise exercise) {
        mutableClassLoader.addUrl(urlToSut);
        exerciseTestRunner.runTests(exercise.getTestClasses());
    }

    public void testPattern(Pattern pattern) {
        mutableClassLoader.addUrl(urlToSut);
        exerciseTestRunner.runTests(pattern.getTestClass());
    }
}
