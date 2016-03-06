package com.github.fhtw.swp.tutorium.reflection;

import com.github.fhtw.swp.tutorium.observer.Subject;

@Subject(factory = SubjectFactory.class)
public class ComplexTestSubject {

    public ComplexTestSubject(Object dummyParameter) { }

    void register(Observer observer) {

    }
}
