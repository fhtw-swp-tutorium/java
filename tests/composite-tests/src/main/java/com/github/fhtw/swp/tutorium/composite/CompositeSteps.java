package com.github.fhtw.swp.tutorium.composite;

import com.github.fhtw.swp.tutorium.shared.SingleTypeContext;
import cucumber.api.java.de.Dann;
import cucumber.api.java.de.Gegebensei;
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
    private final CompositeDriver compositeDriver;
    private Set<Class<?>> leafTypes;

    @Inject
    public CompositeSteps(SingleTypeContext singleTypeContext, LeafTypeProvider leafTypeProvider, CompositeDriver compositeDriver) {
        this.singleTypeContext = singleTypeContext;
        this.compositeDriver = compositeDriver;
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
}
