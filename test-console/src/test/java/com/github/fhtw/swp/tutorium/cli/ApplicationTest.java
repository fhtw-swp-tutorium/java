package com.github.fhtw.swp.tutorium.cli;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ApplicationTest {

    private Path pathToResultsFolder;

    @Before
    public void setUp() throws Exception {
        pathToResultsFolder = Paths.get(".", Application.JUNIT_RESULTS_FOLDER);
        final boolean successful = pathToResultsFolder.toFile().mkdir();

        Assume.assumeTrue("Results folder should exist prior testing", successful);
    }

    @Test
    public void shouldDeleteResultsFolder() throws Exception {
        Application.main(new String[] { });

        assertThat(pathToResultsFolder.toFile().exists(), is(false));
    }
}