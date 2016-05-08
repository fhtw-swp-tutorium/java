package com.github.fhtw.swp.tutorium.composite;

import com.github.fhtw.swp.tutorium.composite.matcher.LeafsOfComponentExists;
import com.github.fhtw.swp.tutorium.composite.proxy.CompositeProxy;
import com.github.fhtw.swp.tutorium.composite.proxy.factory.CompositeProxyFactory;
import com.github.fhtw.swp.tutorium.shared.SingleTypeContext;
import cucumber.api.PendingException;
import cucumber.api.java.de.Dann;
import cucumber.api.java.de.Gegebensei;
import cucumber.api.java.de.Und;
import cucumber.api.java.de.Wenn;
import org.hamcrest.reflection.ImplementsInterface;
import org.hamcrest.reflection.IsInterface;

import javax.inject.Inject;
import java.lang.reflect.Method;
import java.util.Set;

import static org.hamcrest.reflection.AnnotatedMethodMatcher.hasSingleMethodWithAnnotation;
import static org.hamcrest.reflection.ParameterTypeMatcher.hasParameterTypes;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeThat;

public class CompositeSteps {

    private final SingleTypeContext singleTypeContext;
    private final CompositeContext compositeContext;
    private final CompositeDriver compositeDriver;
    private final CompositeProxyFactory compositeProxyFactory;

    private Set<Class<?>> leafTypes;

    @Inject
    public CompositeSteps(SingleTypeContext singleTypeContext, LeafTypeProvider leafTypeProvider, CompositeContext compositeContext, CompositeDriver compositeDriver, CompositeProxyFactory compositeProxyFactory) {
        this.singleTypeContext = singleTypeContext;
        this.compositeContext = compositeContext;
        this.compositeDriver = compositeDriver;
        this.compositeProxyFactory = compositeProxyFactory;
        this.leafTypes = leafTypeProvider.getLeafTypes();
    }


    @Gegebensei("^der Composite-Typ$")
    public void derCompositeTyp() throws Throwable {
        // do nothing for now
    }

    @Gegebensei("^die Methode zum Hinzufügen einer Komponente$")
    public void dieMethodeZumHinzufuegenEinerKomponente() throws Throwable {

        assumeThat(getCompositeType(), hasSingleMethodWithAnnotation(AddComponent.class));

        final Method addComponentMethod = compositeDriver.getAddComponentMethod(getCompositeType());
        singleTypeContext.setMethodUnderTest(addComponentMethod);
    }

    private Class<?> getCompositeType() {
        return singleTypeContext.getTypeUnderTest();
    }

    @Dann("^erwarte ich mir eine Methode deren Parameter dem Komponententyp entspricht$")
    public void erwarteIchMirEineMethodeDerenParameterDemKomponententypEntspricht() throws Throwable {

        final Class<?> definedComponentType = compositeDriver.getDefinedComponentType(getCompositeType());
        final Method addComponentMethod = singleTypeContext.getMethodUnderTest();

        assertThat(addComponentMethod, hasParameterTypes(definedComponentType));
    }

    @Dann("^muss der angegebene Komponententyp ein Interface sein$")
    public void mussDerAngegebeneKomponententypEinInterfaceSein() throws Throwable {
        final Class<?> compositeType = getCompositeType();

        final Class<?> definedComponentType = compositeDriver.getDefinedComponentType(compositeType);

        assertThat(definedComponentType, new IsInterface());
    }

    @Dann("^muss der Type den angegebenen Komponententyp implementieren$")
    public void mussDerTypeDenAngegebenenKomponententypImplementieren() throws Throwable {

        final Class<?> definedComponentType = compositeDriver.getDefinedComponentType(getCompositeType());

        assertThat(getCompositeType(), new ImplementsInterface(definedComponentType));
    }

    @Dann("^erwarte ich mir mindestens ein Leaf$")
    public void erwarteIchMirMindestensEinLeaf() throws Throwable {

        final Class<?> definedComponentType = compositeDriver.getDefinedComponentType(getCompositeType());

        assertThat(leafTypes, new LeafsOfComponentExists(definedComponentType));
    }

    @Gegebensei("^eine Instanz des Composites$")
    public void eineInstanzDesComposites() throws Throwable {
        final CompositeProxy composite = compositeProxyFactory.create(getCompositeType());

        compositeContext.setCompositeProxy(composite);
    }

    @Und("^eine dynamische Component-Instanz$")
    public void eineDynamischeComponentInstanz() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Wenn("^ich diese Instanz zu dem Composite hinzufüge$")
    public void ichDieseInstanzZuDemCompositeHinzufüge() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Und("^ich die ComponentOperation des Composites aufrufe$")
    public void ichDieComponentOperationDesCompositesAufrufe() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Dann("^soll die ComponentOperation der dynamischen Component-Instanz aufgerufen werden$")
    public void sollDieComponentOperationDerDynamischenComponentInstanzAufgerufenWerden() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
