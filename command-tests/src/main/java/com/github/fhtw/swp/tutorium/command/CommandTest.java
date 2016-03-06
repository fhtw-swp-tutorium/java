package com.github.fhtw.swp.tutorium.command;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"cucumber.runtime.ScenarioStatsSummaryPrinter", "junit:results/command_results.xml"})
public class CommandTest {

}
