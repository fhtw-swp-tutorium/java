package com.github.fhtw.swp.tutorium.shared;

import java.lang.annotation.Annotation;

public interface AnnotationResolver {

    Class<? extends Annotation> resolve(String name);
}
