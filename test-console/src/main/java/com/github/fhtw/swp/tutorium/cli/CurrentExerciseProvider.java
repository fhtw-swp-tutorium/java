package com.github.fhtw.swp.tutorium.cli;

import com.github.fhtw.swp.tutorium.Exercise;
import com.github.fhtw.swp.tutorium.inject.CurrentExercise;
import org.codejargon.feather.Provides;

public class CurrentExerciseProvider {

    private final Exercise current;

    public CurrentExerciseProvider(Exercise current) {
        this.current = current;
    }

    @Provides
    @CurrentExercise
    public Exercise get() {
        return current;
    }
}
