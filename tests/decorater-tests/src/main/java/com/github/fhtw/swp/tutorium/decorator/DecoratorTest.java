package com.github.fhtw.swp.tutorium.decorator;

import com.github.fhtw.swp.tutorium.shared.PatternDefiningAnnotation;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "cucumber.runtime.ScenarioStatsSummaryPrinter",
                "com.github.fhtw.swp.tutorium.reporting.LoggingReporter"
        },
        features = "classpath:Decorator.feature",
        glue = {
                "com.github.fhtw.swp.tutorium.decorator",
                "com.github.fhtw.swp.tutorium.shared"
        }
)
@PatternDefiningAnnotation(Decorator.class)
public class DecoratorTest {

}
