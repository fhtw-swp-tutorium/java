package com.github.fhtw.swp.tutorium.cli;

import com.github.fhtw.swp.tutorium.SwpTestTool;
import com.github.fhtw.swp.tutorium.guice.CucumberInjectorSource;
import com.github.fhtw.swp.tutorium.guice.SwpTestToolModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import cucumber.api.guice.CucumberModules;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Application {

    public static String JUNIT_RESULTS_FOLDER = "junit-out";

    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {

        clearResultsFolder();

        final Arguments arguments = new Arguments();
        final CmdLineParser cmdLineParser = new CmdLineParser(arguments);

        LOGGER.debug("Arguments: {}", args);

        new VersionPrinter().printVersionInformation(System.out);

        try {
            cmdLineParser.parseArgument(args);
        } catch (CmdLineException e) {
            LOGGER.error("Failed to parse arguments", e);
            printUsage(e);
            return;
        }

        final Injector injector = Guice.createInjector(
                new SwpTestToolModule(arguments.getJarUrl()),
                CucumberModules.SCENARIO
        );

        CucumberInjectorSource.instance = injector;

        if (arguments.getExercise() != null) {
            injector.getInstance(SwpTestTool.class).testExercise(arguments.getExercise());
        }

        if (arguments.getPattern() != null) {
            injector.getInstance(SwpTestTool.class).testPattern(arguments.getPattern());
        }
    }

    private static void printUsage(CmdLineException e) {

        System.err.println(e.getMessage());
        System.err.println("Help: ");
        e.getParser().printUsage(System.err);
    }

    private static void clearResultsFolder() {

        final Path pathToResultsFolder = Paths.get(".", JUNIT_RESULTS_FOLDER);
        final File resultsFolder = pathToResultsFolder.toFile();

        if (resultsFolder.exists()) {
            final boolean successful = resultsFolder.delete();

            if (!successful) {
                LOGGER.warn("Unable to delete results folder at {}.", pathToResultsFolder.toAbsolutePath());
            }
        }
    }
}
