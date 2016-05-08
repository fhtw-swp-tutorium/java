package com.github.fhtw.swp.tutorium.decorator;

import com.github.fhtw.swp.tutorium.shared.SingleTypeContext;
import cucumber.api.java.de.Dann;
import cucumber.api.java.de.Gegebensei;
import org.hamcrest.reflection.FieldTypeMatcher;
import org.hamcrest.reflection.ImplementsInterface;
import org.hamcrest.reflection.IsInterface;

import javax.inject.Inject;
import java.lang.reflect.Field;

import static org.junit.Assert.assertThat;

public class DecoratorSteps {

    private final SingleTypeContext singleTypeContext;
    private final DecoratorDriver decoratorDriver;

    @Inject
    public DecoratorSteps(SingleTypeContext singleTypeContext, DecoratorDriver decoratorDriver) {
        this.singleTypeContext = singleTypeContext;
        this.decoratorDriver = decoratorDriver;
    }

    @Gegebensei("^der Dekorator-Typ$")
    public void derDekoratorTyp() throws Throwable {
        // do nothing for now
    }

    @Dann("^muss der angegebene Komponententyp ein Interface sein$")
    public void mussDerAngegebeneKomponententypEinInterfaceSein() throws Throwable {

        final Class<?> decoratorType = singleTypeContext.getTypeUnderTest();
        final Class<?> definedComponentType = decoratorDriver.getDefinedComponentType(decoratorType);

        assertThat(definedComponentType, new IsInterface());
    }

    @Dann("^muss der Typ den angegebenen Komponententyp implementieren$")
    public void mussDerTypDenAngegebenenKomponententypImplementieren() throws Throwable {

        final Class<?> decoratorType = singleTypeContext.getTypeUnderTest();
        final Class<?> definedComponentType = decoratorDriver.getDefinedComponentType(decoratorType);

        assertThat(decoratorType, new ImplementsInterface(definedComponentType));
    }

    @Gegebensei("^das annotierte Feld$")
    public void dasAnnotierteFeld() throws Throwable {

        final Class<?> decoratorType = singleTypeContext.getTypeUnderTest();
        final Field componentField = decoratorDriver.getComponentField(decoratorType);

        singleTypeContext.setFieldUnderTest(componentField);
    }

    @Dann("^muss der Typ des Feldes dem angebenen Komponententyp entsprechen$")
    public void mussDerTypDesFeldesDemAngebenenKomponententypEntsprechen() throws Throwable {

        final Class<?> decoratorType = singleTypeContext.getTypeUnderTest();
        final Class<?> definedComponentType = decoratorDriver.getDefinedComponentType(decoratorType);

        final Field componentField = singleTypeContext.getFieldUnderTest();

        assertThat(componentField, new FieldTypeMatcher(definedComponentType));
    }
}
