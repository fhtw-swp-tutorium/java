package com.github.fhtw.swp.tutorium.strategy;

import com.github.fhtw.swp.tutorium.shared.SingleTypeContext;
import cucumber.api.java.de.Dann;
import cucumber.api.java.de.Gegebensei;
import org.hamcrest.reflection.ImplementsInterface;
import org.hamcrest.reflection.IsInterface;

import javax.inject.Inject;

import static org.junit.Assert.assertThat;

public class StrategySteps {

    private final SingleTypeContext singleTypeContext;
    private final StrategyDriver strategyDriver;

    @Inject
    public StrategySteps(SingleTypeContext singleTypeContext, StrategyDriver strategyDriver) {
        this.singleTypeContext = singleTypeContext;
        this.strategyDriver = strategyDriver;
    }

    @Gegebensei("^der konkrete Strategie-Typ$")
    public void derKonkreteStrategieTyp() throws Throwable {
        // do nothing for now
    }

    @Dann("^muss der angegebene Strategie-Typ ein Interface sein$")
    public void mussDerAngegebeneStrategieTypEinInterfaceSein() throws Throwable {

        final Class<?> concreteStrategy = singleTypeContext.getTypeUnderTest();
        final Class<?> strategyType = strategyDriver.getDefinedStrategyType(concreteStrategy);

        assertThat(strategyType, new IsInterface());
    }

    @Dann("^muss der Typ den angegebenen Strategie-Typ implementieren$")
    public void mussDerTypDenAngegebenenStrategieTypImplementieren() throws Throwable {

        final Class<?> concreteStrategy = singleTypeContext.getTypeUnderTest();
        final Class<?> strategyType = strategyDriver.getDefinedStrategyType(concreteStrategy);

        assertThat(concreteStrategy, new ImplementsInterface(strategyType));
    }
}
