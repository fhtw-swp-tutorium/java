package com.github.fhtw.swp.tutorium.e2eTest.support;

import com.github.fhtw.swp.tutorium.cli.Application;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.nio.file.Paths;

public class JUnitResultLocator {

    public JUnitResult get(String xmlFileName) throws JAXBException {
        final File junitResultFile = Paths.get(".", Application.JUNIT_RESULTS_FOLDER, xmlFileName).normalize().toFile();
        return JUnitResult.fromFile(junitResultFile);
    }
}
