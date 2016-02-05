package com.github.fhtw.swp.tutorium.cli;

import static java.lang.ClassLoader.getSystemClassLoader;

import com.github.fhtw.swp.tutorium.MutableClassLoader;
import com.github.fhtw.swp.tutorium.TestResultPrinter;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.OptionHandlerFilter;

import com.github.fhtw.swp.tutorium.common.JarFileUrlHolder;
import com.github.fhtw.swp.tutorium.ExerciseTestRunner;

/**
 * @author Thomas Eizinger, Senacor Technologies AG.
 */
public class SwpTestTool {

    private static final Arguments arguments = new Arguments();
    private static final CmdLineParser cmdLineParser = new CmdLineParser(arguments);
    private static final TestResultPrinter testResultPrinter = new TestResultPrinter(System.out);
    private static final ExerciseTestRunner exerciseTestRunner = new ExerciseTestRunner(testResultPrinter);
    private static final MutableClassLoader mutableClassLoader = new MutableClassLoader(getSystemClassLoader());

    public static void main(String[] args) {

        final String currentVersion = SwpTestTool.class.getPackage().getImplementationVersion();

        System.out.println("Running version " + currentVersion);

        try {
            cmdLineParser.parseArgument(args);
        } catch (CmdLineException e) {
            printUsage(e);
            return;
        }

        JarFileUrlHolder.setUrl(arguments.getJarUrl());
        mutableClassLoader.addUrl(arguments.getJarUrl());

        exerciseTestRunner.runExerciseTests(arguments.getExercise());
    }

    private static void printUsage(CmdLineException e) {

        System.err.println(e.getMessage());
        System.err.println("Usage: ");
        e.getParser().printUsage(System.err);
        System.err.println("Example: ");
        System.err.println(e.getParser().printExample(OptionHandlerFilter.ALL));
    }
}
