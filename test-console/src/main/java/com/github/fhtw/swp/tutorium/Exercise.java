package com.github.fhtw.swp.tutorium;

import java.util.Arrays;
import java.util.function.IntFunction;

import static com.github.fhtw.swp.tutorium.Pattern.*;

public enum Exercise {
    UE1(OBSERVER, SINGLETON, COMMAND),
    UE2(COMPOSITE, DECORATOR),
    UE3();

    private final Pattern[] patterns;

    Exercise(Pattern... patterns) {
        this.patterns = patterns;
    }

    public Class<?>[] getTestClasses() {
        return Arrays.stream(patterns).map(Pattern::getTestClass).toArray((IntFunction<Class<?>[]>) Class[]::new);
    }
}
