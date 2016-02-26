package com.github.fhtw.swp.tutorium.common;

import com.github.fhtw.swp.tutorium.command.InvokeCommand;
import com.github.fhtw.swp.tutorium.command.Invoker;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

public enum AnnotationResolver {
    INSTANCE;

    private final Map<String, Class<? extends Annotation>> annotationMap = new HashMap<String, Class<? extends Annotation>>() {{
        put("Invoker", Invoker.class);
        put("InvokeCommand", InvokeCommand.class);
    }};

    public Class<? extends Annotation> resolve(String name) {
        return annotationMap.get(name);
    }
}
