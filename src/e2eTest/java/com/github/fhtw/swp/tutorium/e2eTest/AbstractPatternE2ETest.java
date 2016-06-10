package com.github.fhtw.swp.tutorium.e2eTest;

import com.github.fhtw.swp.tutorium.Pattern;
import com.github.fhtw.swp.tutorium.e2eTest.support.JUnitResult;
import com.github.fhtw.swp.tutorium.e2eTest.support.JUnitResultLocator;
import com.github.fhtw.swp.tutorium.e2eTest.support.PatternE2ETestConfiguration;
import com.github.fhtw.swp.tutorium.e2eTest.support.SwpTestToolProxy;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.net.URL;

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

        final JUnitResult junitResult = jUnitResultLocator.get(junitResultFileName);

        assertJUnitResult(junitResult);
    }

    protected abstract void assertJUnitResult(JUnitResult result);
}
