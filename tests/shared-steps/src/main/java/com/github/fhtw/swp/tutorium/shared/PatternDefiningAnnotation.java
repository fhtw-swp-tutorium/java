package com.github.fhtw.swp.tutorium.shared;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PatternDefiningAnnotation {

    Class<? extends Annotation> value();

}
