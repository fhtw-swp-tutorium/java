package com.github.fhtw.swp.tutorium.composite;

import com.github.fhtw.swp.tutorium.shared.SingleTypeContext;

import javax.inject.Inject;

public class CompositeContext {

    private final SingleTypeContext typeContext;

    @Inject
    public CompositeContext(SingleTypeContext typeContext) {
        this.typeContext = typeContext;
    }


}
