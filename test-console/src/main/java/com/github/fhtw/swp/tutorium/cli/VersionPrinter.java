package com.github.fhtw.swp.tutorium.cli;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.PrintStream;

public class VersionPrinter {

    private static final Logger LOGGER = LogManager.getLogger();

    private static String currentVersion;
    private static String requiredAnnotationVersion;

    static {
        currentVersion = VersionPrinter.class.getPackage().getImplementationVersion();
        requiredAnnotationVersion = DependencyVersionResolver.getVersion("com.github.fhtw-swp-tutorium", "annotations");
    }

    public void printVersionInformation(PrintStream target) {

        final String runningVersionText = "Running version: " + currentVersion;

        LOGGER.debug(runningVersionText);
        target.println(runningVersionText);

        final String requiredAnnotationVersionText = "Required annotation version: " + requiredAnnotationVersion;

        LOGGER.debug(requiredAnnotationVersionText);
        target.println(requiredAnnotationVersionText);
    }
}
