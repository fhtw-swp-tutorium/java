package com.github.fhtw.swp.tutorium.reflection;

public interface ClassInstanceFactory {

    <T> T create(Class<T> type);

}
