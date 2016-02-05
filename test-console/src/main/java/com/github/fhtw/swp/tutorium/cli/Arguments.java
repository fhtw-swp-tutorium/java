package com.github.fhtw.swp.tutorium.cli;

import com.github.fhtw.swp.tutorium.Exercise;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

import java.net.URL;

public class Arguments {

    @Option(name = "-exercise", required = true, usage = "The exercise to test")
    private Exercise exercise;

    @Argument(required = true, usage = "The path to the .jar file of the exercise", handler = UrlOptionHandler.class)
    private URL jarUrl;

    public Exercise getExercise() {
        return exercise;
    }

    public URL getJarUrl() {
        return jarUrl;
    }
}
