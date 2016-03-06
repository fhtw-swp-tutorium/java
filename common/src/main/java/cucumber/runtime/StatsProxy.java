package cucumber.runtime;

import com.github.fhtw.swp.tutorium.reflection.FieldProxy;
import com.github.fhtw.swp.tutorium.reflection.MethodProxy;

import java.io.PrintStream;
import java.util.List;

public final class StatsProxy {

    private final Stats instance;
    private final MethodProxy printScenarioCountsMethod;
    private final FieldProxy failedScenariosField;

    private StatsProxy(Stats instance, MethodProxy printScenarioCountsMethod, FieldProxy failedScenariosField) {
        this.instance = instance;
        this.printScenarioCountsMethod = printScenarioCountsMethod;
        this.failedScenariosField = failedScenariosField;
    }

    public static StatsProxy create(Runtime runtime) {

        final FieldProxy statsField = FieldProxy.create(Runtime.class, "stats");
        final Stats stats = statsField.get(runtime);

        final MethodProxy printScenarioCountsMethod = MethodProxy.create(Stats.class, "printScenarioCounts", PrintStream.class);
        final FieldProxy failedScenariosField = FieldProxy.create(Stats.class, "failedScenarios");

        return new StatsProxy(stats, printScenarioCountsMethod, failedScenariosField);
    }

    public void printScenarioCounts(PrintStream printStream) {
        printScenarioCountsMethod.invoke(instance, printStream);
    }

    public List<String> getFailedScenarios() {
        return failedScenariosField.get(instance);
    }
}
