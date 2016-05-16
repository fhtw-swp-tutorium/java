package com.github.fhtw.swp.tutorium.command;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "cucumber.runtime.ScenarioStatsSummaryPrinter",
                "junit:junit-out/command_results.xml",
                "com.github.fhtw.swp.tutorium.reporting.LoggingReporter"
        },
        features = "classpath:Command.feature",
        glue = {
                "com.github.fhtw.swp.tutorium.command",
                "com.github.fhtw.swp.tutorium.shared"
        }
)
public class CommandTest {

}
