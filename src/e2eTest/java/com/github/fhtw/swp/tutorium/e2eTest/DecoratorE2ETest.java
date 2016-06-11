package com.github.fhtw.swp.tutorium.e2eTest;

import com.github.fhtw.swp.tutorium.Pattern;
import com.github.fhtw.swp.tutorium.e2eTest.support.Expectation;
import com.github.fhtw.swp.tutorium.e2eTest.support.PatternE2ETestConfiguration;

@PatternE2ETestConfiguration(
        patternToTest = Pattern.DECORATOR,
        jUnitResultFileName = "MarkdownTitlePrinter.xml",
        pathToImplementationJar = "/accurate-decorators-0.0.1-SNAPSHOT.jar",
        expectation = @Expectation(
                numberOfTests = 4
        )
)
public class DecoratorE2ETest extends AbstractPatternE2ETest {

}
