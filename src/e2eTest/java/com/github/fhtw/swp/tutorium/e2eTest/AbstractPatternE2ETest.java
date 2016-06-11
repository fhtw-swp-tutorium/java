package com.github.fhtw.swp.tutorium.e2eTest;

import com.github.fhtw.swp.tutorium.Pattern;
import com.github.fhtw.swp.tutorium.e2eTest.support.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.net.URL;

import static com.github.fhtw.swp.tutorium.e2eTest.support.JUnitResultMatcher.*;
import static org.hamcrest.Matchers.is;

public abstract class AbstractPatternE2ETest {

    private SwpTestToolProxy swpTestTool;
    private JUnitResultLocator jUnitResultLocator;

    @Before
    public void setUp() throws Exception {
        swpTestTool = SwpTestToolProxy.createInstance();
        jUnitResultLocator = new JUnitResultLocator();
    }

    @Test
    public void testPatternImplementation() throws JAXBException {

        final PatternE2ETestConfiguration patternE2ETest = getClass().getAnnotation(PatternE2ETestConfiguration.class);
        String junitResultFileName = patternE2ETest.jUnitResultFileName();
        String patternImplementation = patternE2ETest.pathToImplementationJar();
        Pattern pattern = patternE2ETest.patternToTest();

        if (!patternImplementation.startsWith("/")) {
            patternImplementation = "/" + patternImplementation;
        }

        final URL urlToPatternImplementation = CompositeE2ETest.class.getResource(patternImplementation);

        swpTestTool.run(pattern, urlToPatternImplementation);

        final JUnitResult result = jUnitResultLocator.get(junitResultFileName);

        final Expectation expectation = patternE2ETest.expectation();

        Assert.assertThat(result, numberOfTests(is(expectation.numberOfTests())));
        Assert.assertThat(result, numberOfSkippedTests(is(expectation.numberOfSkippedTests())));
        Assert.assertThat(result, numberOfFailedTests(is(expectation.numberOfFailedTests())));
    }
}
