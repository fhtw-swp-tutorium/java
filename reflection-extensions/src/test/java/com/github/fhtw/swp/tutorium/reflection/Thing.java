package com.github.fhtw.swp.tutorium.reflection;

import com.github.fhtw.swp.tutorium.Factory;
import com.github.fhtw.swp.tutorium.NullFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Thing {

    Class<? extends Factory> factory() default NullFactory.class;
}