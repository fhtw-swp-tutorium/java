package com.github.fhtw.swp.tutorium.e2eTest;

import com.github.fhtw.swp.tutorium.Pattern;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;

public class CompositesE2ETest {

    private SwpTestToolProxy swpTestTool;

    @Before
    public void setUp() throws Exception {
        swpTestTool = SwpTestToolProxy.createInstance();
    }

    @Test
    public void testAccurateComposites() throws Exception {

        final URL patternImplementation = CompositesE2ETest.class.getResource("/accurate-composites-0.0.1-SNAPSHOT.jar");

        swpTestTool.run(Pattern.COMPOSITE, patternImplementation);
    }
}
