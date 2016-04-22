package cucumber.runtime;

import cucumber.runtime.io.ResourceLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringStartsWith.startsWith;

public class StatsProxyTest {

    @Mock
    private ResourceLoader resourceLoader;

    @Mock
    private Backend backend;
    private Runtime sut;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        final ClassLoader classLoader = getClass().getClassLoader();
        final RuntimeOptions runtimeOptions = new RuntimeOptions("");
        final Set<Backend> backends = Collections.singleton(backend);

        sut = new Runtime(resourceLoader, classLoader, backends, runtimeOptions);
    }

    @Test
    public void testReflectionAccess() throws Exception {
        StatsProxy.create(sut);
    }

    @Test
    public void testShouldPrintEmptyStats() throws Exception {

        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final PrintStream printStream = new PrintStream(byteArrayOutputStream);

        StatsProxy.create(sut).printScenarioCounts(printStream);

        final String printedStats = byteArrayOutputStream.toString("UTF-8");

        Assert.assertThat(printedStats, startsWith("0 Scenarios ()"));
    }

    @Test
    public void testShouldReturnNoFailedScenarios() throws Exception {

        final List<String> failedScenarios = StatsProxy.create(sut).getFailedScenarios();

        Assert.assertThat(failedScenarios.size(), is(0));
    }
}