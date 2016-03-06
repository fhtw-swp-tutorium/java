package cucumber.runtime;

import cucumber.api.SummaryPrinter;

import java.util.List;

public class ScenarioStatsSummaryPrinter implements SummaryPrinter {
    @Override
    public void print(Runtime runtime) {
        final StatsProxy statsProxy = StatsProxy.create(runtime);
        statsProxy.printScenarioCounts(System.out);

        final List<String> failedScenarios = statsProxy.getFailedScenarios();

        for (String failedScenario : failedScenarios) {
            final String[] parts = failedScenario.split("# Szenario: ");
            System.out.println(parts[1]);
        }
    }
}
