package com.github.fhtw.swp.tutorium.observer;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "cucumber.runtime.ScenarioStatsSummaryPrinter",
                "junit:junit-out/observer_results.xml",
                "com.github.fhtw.swp.tutorium.reporting.LoggingReporter"
        },
        features = "classpath:Observer.feature",
        glue = {
                "com.github.fhtw.swp.tutorium.observer",
                "com.github.fhtw.swp.tutorium.shared"
        }
)
public class ObserverTest {

}
