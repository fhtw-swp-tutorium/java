package com.github.fhtw.swp.tutorium.reflection;

import java.lang.reflect.InvocationHandler;

public interface ProxyFactory {

    Object create(Class<?> interfaceToProxy, InvocationHandler invocationHandler);
}
