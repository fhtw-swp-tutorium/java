package com.github.fhtw.swp.tutorium.composite;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "cucumber.runtime.ScenarioStatsSummaryPrinter",
                "com.github.fhtw.swp.tutorium.reporting.LoggingReporter"
        },
        glue = {
                "com.github.fhtw.swp.tutorium.composite",
                "com.github.fhtw.swp.tutorium.shared"
        }
)
public class CompositeTest {
}
