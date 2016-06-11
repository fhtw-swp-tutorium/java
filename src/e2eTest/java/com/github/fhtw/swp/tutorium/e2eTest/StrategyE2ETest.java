package com.github.fhtw.swp.tutorium.e2eTest;

import com.github.fhtw.swp.tutorium.Pattern;
import com.github.fhtw.swp.tutorium.e2eTest.support.Expectation;
import com.github.fhtw.swp.tutorium.e2eTest.support.PatternE2ETestConfiguration;

@PatternE2ETestConfiguration(
        patternToTest = Pattern.STRATEGY,
        jUnitResultFileName = "TelegramTransportStrategy.xml",
        pathToImplementationJar = "/accurate-strategies-0.0.1-SNAPSHOT.jar",
        expectation = @Expectation(
                numberOfTests = 2
        )
)
public class StrategyE2ETest extends AbstractPatternE2ETest {

}
