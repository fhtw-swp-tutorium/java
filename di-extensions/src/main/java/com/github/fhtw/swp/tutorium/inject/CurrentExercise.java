package com.github.fhtw.swp.tutorium.inject;

import javax.inject.Qualifier;
import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Qualifier
public @interface CurrentExercise {

}
