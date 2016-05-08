package org.hamcrest.reflection;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class ToStringHelperTest {

    @Test
    public void methodToString() throws Exception {

        final String stringRepresentation = ToStringHelper.toString(Bar.class.getMethod("methodWithClass", Bar.class));

        assertThat(stringRepresentation, Matchers.is("Bar#methodWithClass"));
    }
}