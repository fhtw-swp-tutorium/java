package com.github.fhtw.swp.tutorium.cli;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.PrintStream;

public class VersionPrinter {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String currentVersion = VersionPrinter.class.getPackage().getImplementationVersion();

    public void printCurrentVersion(PrintStream target) {

        final String versionText = "Running version: " + currentVersion;

        LOGGER.debug(versionText);
        target.println(versionText);
    }
}
