package cucumber.runtime;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

final class MethodProxy {

    private final Method method;

    private MethodProxy(Method method) {
        this.method = method;
    }

    public static MethodProxy create(Class<?> type, String methodName, Class<?>... parameterTypes) {

        final Method method = getDeclaredMethod(type, methodName, parameterTypes);
        method.setAccessible(true);

        return new MethodProxy(method);
    }

    private static Method getDeclaredMethod(Class<?> type, String methodName, Class<?>[] parameterTypes) {
        try {
            return type.getDeclaredMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public <T> T invoke(Object instance, Object... parameter) {
        try {
            return (T) method.invoke(instance, parameter);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
