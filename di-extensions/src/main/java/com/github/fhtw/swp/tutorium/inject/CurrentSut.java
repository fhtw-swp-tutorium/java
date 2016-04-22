package com.github.fhtw.swp.tutorium.inject;

import javax.inject.Qualifier;
import java.lang.annotation.*;

/**
 * SUT is short for system under test.
 *
 * The qualifier is designed to be used for all object-instances that are related to the actual system under test.
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Qualifier
public @interface CurrentSut {

}
