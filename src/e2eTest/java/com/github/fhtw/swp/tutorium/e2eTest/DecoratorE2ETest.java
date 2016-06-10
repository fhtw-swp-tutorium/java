package com.github.fhtw.swp.tutorium.e2eTest;

import com.github.fhtw.swp.tutorium.Pattern;
import com.github.fhtw.swp.tutorium.cli.Application;
import com.github.fhtw.swp.tutorium.e2eTest.support.JUnitResult;
import com.github.fhtw.swp.tutorium.e2eTest.support.SwpTestToolProxy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;

import static com.github.fhtw.swp.tutorium.e2eTest.support.JUnitResultMatcher.*;
import static org.hamcrest.Matchers.is;

public class DecoratorE2ETest {

    private SwpTestToolProxy swpTestTool;

    @Before
    public void setUp() throws Exception {
        swpTestTool = SwpTestToolProxy.createInstance();
    }

    @Test
    public void testAccurateDecorator() throws Exception {

        final URL patternImplementation = CompositeE2ETest.class.getResource("/accurate-decorators-0.0.1-SNAPSHOT.jar");

        swpTestTool.run(Pattern.DECORATOR, patternImplementation);

        final File junitResultFile = Paths.get(".", Application.JUNIT_RESULTS_FOLDER, "MarkdownTitlePrinter.xml").toFile();

        final JUnitResult junitResult = JUnitResult.fromFile(junitResultFile);

        Assert.assertThat(junitResult, numberOfTests(is(4)));
        Assert.assertThat(junitResult, numberOfSkippedTests(is(0)));
        Assert.assertThat(junitResult, numberOfFailedTests(is(0)));
    }
}
