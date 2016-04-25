package com.github.fhtw.swp.tutorium.reflection;

import com.github.fhtw.swp.tutorium.Factory;

public class SubjectFactory implements Factory {

    @Override
    public Object getInstance() {
        return new ComplexTestSubject(new Object());
    }
}
