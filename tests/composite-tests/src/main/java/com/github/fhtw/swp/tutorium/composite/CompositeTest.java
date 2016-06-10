package com.github.fhtw.swp.tutorium.composite;

import com.github.fhtw.swp.tutorium.shared.PatternDefiningAnnotation;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "cucumber.runtime.ScenarioStatsSummaryPrinter",
                "com.github.fhtw.swp.tutorium.reporting.LoggingReporter",
                "com.github.fhtw.swp.tutorium.reporting.JunitReporter"
        },
        features = "classpath:Composite.feature",
        glue = {
                "com.github.fhtw.swp.tutorium.composite",
                "com.github.fhtw.swp.tutorium.shared"
        }
)
@PatternDefiningAnnotation(Composite.class)
public class CompositeTest {

}
