package com.github.fhtw.swp.tutorium.cli;

import com.github.fhtw.swp.tutorium.Exercise;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kohsuke.args4j.CmdLineParser;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.core.Is.is;

public class ArgumentsTest {

    private Arguments arguments;
    private CmdLineParser cmdLineParser;

    @Before
    public void setUp() throws Exception {
        arguments = new Arguments();
        cmdLineParser = new CmdLineParser(arguments);
    }

    @Test
    public void testShouldAcceptDefinedArguments() throws Exception {

        final String argumentString = "exercise1.jar -exercise UE1";
        final String[] args = argumentString.split(" ");

        cmdLineParser.parseArgument(args);

        final String actualFileName = arguments.getJarUrl().getFile();
        final Exercise chosenExercise = arguments.getExercise();

        Assert.assertThat(actualFileName, endsWith("exercise1.jar"));
        Assert.assertThat(chosenExercise, is(Exercise.UE1));
    }
}