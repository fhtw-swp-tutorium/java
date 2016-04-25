package com.github.fhtw.swp.tutorium.reflection;

import com.github.fhtw.swp.tutorium.Factory;

public class ComplexThingFactory implements Factory {

    @Override
    public Object getInstance() {
        return new ComplexThing(new Object());
    }
}
