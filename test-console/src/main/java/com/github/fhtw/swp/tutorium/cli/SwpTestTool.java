package com.github.fhtw.swp.tutorium.cli;

import com.github.fhtw.swp.tutorium.ExerciseTestRunner;
import com.github.fhtw.swp.tutorium.MutableClassLoader;
import com.github.fhtw.swp.tutorium.common.SwpTestContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import javax.inject.Inject;

import static java.lang.ClassLoader.getSystemClassLoader;

public class SwpTestTool {

    private static final Logger LOGGER = LogManager.getLogger();

    private final Arguments arguments = new Arguments();
    private final CmdLineParser cmdLineParser = new CmdLineParser(arguments);

    private final MutableClassLoader mutableClassLoader = new MutableClassLoader(getSystemClassLoader());

    private final SwpTestContext context;
    private final VersionPrinter versionPrinter;
    private final ExerciseTestRunner exerciseTestRunner;

    @Inject
    public SwpTestTool(SwpTestContext context, VersionPrinter versionPrinter, ExerciseTestRunner exerciseTestRunner) {
        this.context = context;
        this.versionPrinter = versionPrinter;
        this.exerciseTestRunner = exerciseTestRunner;
    }

    public void run(String[] args) {

        LOGGER.debug("Arguments: {}", args);

        versionPrinter.printVersionInformation(System.out);

        try {
            cmdLineParser.parseArgument(args);
        } catch (CmdLineException e) {
            LOGGER.error("Failed to parse arguments", e);
            printUsage(e);
            return;
        }

        mutableClassLoader.addUrl(arguments.getJarUrl());
        context.setJarUrl(arguments.getJarUrl());

        exerciseTestRunner.runExerciseTests(arguments.getExercise());
    }

    private static void printUsage(CmdLineException e) {

        System.err.println(e.getMessage());
        System.err.println("Help: ");
        e.getParser().printUsage(System.err);
    }
}
