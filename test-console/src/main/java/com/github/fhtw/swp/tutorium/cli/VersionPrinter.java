package com.github.fhtw.swp.tutorium.cli;

import java.io.PrintStream;

public class VersionPrinter {

    private static final String currentVersion = VersionPrinter.class.getPackage().getImplementationVersion();

    public void printCurrentVersion(PrintStream target) {
        target.println("Running version: " + currentVersion);
    }
}
