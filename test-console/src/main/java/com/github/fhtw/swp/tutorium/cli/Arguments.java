package com.github.fhtw.swp.tutorium.cli;

import java.net.URL;

import com.github.fhtw.swp.tutorium.Exercises;

import org.kohsuke.args4j.Option;

/**
 * @author Thomas Eizinger, Senacor Technologies AG.
 */
public class Arguments {

    @Option(name = "-exercise", required = true, usage = "The exercise to test")
    private Exercises exercise;

    @Option(name = "-jar", required = true, usage = "The path to the .jar file of the exercise", handler = UrlOptionHandler.class)
    private URL jarUrl;

    public Exercises getExercise() {
        return exercise;
    }

    public URL getJarUrl() {
        return jarUrl;
    }
}
