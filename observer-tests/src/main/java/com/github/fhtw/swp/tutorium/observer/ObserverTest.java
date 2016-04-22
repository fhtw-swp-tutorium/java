package com.github.fhtw.swp.tutorium.observer;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"cucumber.runtime.ScenarioStatsSummaryPrinter", "junit:results/observer_results.xml", "com.github.fhtw.swp.tutorium.common.LoggingReporter"})
public class ObserverTest {

}
