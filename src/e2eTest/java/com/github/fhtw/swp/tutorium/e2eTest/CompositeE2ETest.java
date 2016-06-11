package com.github.fhtw.swp.tutorium.e2eTest;

import com.github.fhtw.swp.tutorium.Pattern;
import com.github.fhtw.swp.tutorium.e2eTest.support.Expectation;
import com.github.fhtw.swp.tutorium.e2eTest.support.PatternE2ETestConfiguration;

@PatternE2ETestConfiguration(
        patternToTest = Pattern.COMPOSITE,
        jUnitResultFileName = "MyCompositeFigure.xml",
        pathToImplementationJar = "/accurate-composites-0.0.1-SNAPSHOT.jar",
        expectation = @Expectation(
                numberOfTests = 8
        )
)
public class CompositeE2ETest extends AbstractPatternE2ETest {

}
