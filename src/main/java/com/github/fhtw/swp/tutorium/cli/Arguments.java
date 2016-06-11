package com.github.fhtw.swp.tutorium.cli;

import com.github.fhtw.swp.tutorium.Exercise;
import com.github.fhtw.swp.tutorium.Pattern;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

import java.net.URL;

public class Arguments {

    @Option(name = "-exercise", forbids = "-pattern", usage = "The exercise to test")
    private Exercise exercise;

    @Option(name = "-pattern", forbids = "-exercise", usage = "The pattern to test")
    private Pattern pattern;

    @Argument(required = true, metaVar = "jarUrl", usage = "The path to the .jar file of the exercise", handler = UrlOptionHandler.class)
    private URL jarUrl;

    public Exercise getExercise() {
        return exercise;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public URL getJarUrl() {
        return jarUrl;
    }
}
