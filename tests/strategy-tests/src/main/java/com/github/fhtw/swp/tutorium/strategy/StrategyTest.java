package com.github.fhtw.swp.tutorium.strategy;

import com.github.fhtw.swp.tutorium.shared.PatternDefiningAnnotation;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "cucumber.runtime.ScenarioStatsSummaryPrinter",
                "com.github.fhtw.swp.tutorium.reporting.LoggingReporter",
                "com.github.fhtw.swp.tutorium.reporting.JUnitReporter"
        },
        features = "classpath:Strategy.feature",
        glue = {
                "com.github.fhtw.swp.tutorium.strategy",
                "com.github.fhtw.swp.tutorium.shared"
        }
)
@PatternDefiningAnnotation(ConcreteStrategy.class)
public class StrategyTest {
}
