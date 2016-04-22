package com.github.fhtw.swp.tutorium;

import com.github.fhtw.swp.tutorium.inject.CurrentExercise;
import com.github.fhtw.swp.tutorium.inject.CurrentSut;

import javax.inject.Inject;
import java.net.URL;

import static java.lang.ClassLoader.getSystemClassLoader;

public class SwpTestTool {

    private final MutableClassLoader mutableClassLoader = new MutableClassLoader(getSystemClassLoader());

    private final URL urlToSut;
    private final Exercise exercise;
    private final ExerciseTestRunner exerciseTestRunner;

    @Inject
    public SwpTestTool(@CurrentSut URL urlToSut,
                       @CurrentExercise  Exercise exercise,
                       ExerciseTestRunner exerciseTestRunner) {
        this.urlToSut = urlToSut;
        this.exercise = exercise;
        this.exerciseTestRunner = exerciseTestRunner;
    }

    public void run() {
        mutableClassLoader.addUrl(urlToSut);
        exerciseTestRunner.runExerciseTests(exercise);
    }
}
