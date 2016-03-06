package com.github.fhtw.swp.tutorium.singleton;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"cucumber.runtime.ScenarioStatsSummaryPrinter", "junit:singleton_results.xml"})
public class SingletonTest {
}
