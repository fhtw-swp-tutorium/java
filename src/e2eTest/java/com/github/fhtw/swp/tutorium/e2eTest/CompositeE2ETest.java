package com.github.fhtw.swp.tutorium.e2eTest;

import com.github.fhtw.swp.tutorium.Pattern;
import com.github.fhtw.swp.tutorium.e2eTest.support.JUnitResult;
import com.github.fhtw.swp.tutorium.e2eTest.support.PatternE2ETestConfiguration;
import org.junit.Assert;

import static com.github.fhtw.swp.tutorium.e2eTest.support.JUnitResultMatcher.*;
import static org.hamcrest.Matchers.is;

@PatternE2ETestConfiguration(
        patternToTest = Pattern.COMPOSITE,
        jUnitResultFileName = "MyCompositeFigure.xml",
        pathToImplementationJar = "/accurate-composites-0.0.1-SNAPSHOT.jar"
)
public class CompositeE2ETest extends AbstractPatternE2ETest {

    @Override
    protected void assertJUnitResult(JUnitResult result) {
        Assert.assertThat(result, numberOfTests(is(8)));
        Assert.assertThat(result, numberOfSkippedTests(is(0)));
        Assert.assertThat(result, numberOfFailedTests(is(0)));
    }
}
