package cucumber.runtime;

import java.lang.reflect.Field;

final class FieldProxy {

    private final Field field;

    private FieldProxy(Field field) {
        this.field = field;
    }

    public static FieldProxy create(Class<?> type, String fieldName) {

        final Field field = getDeclaredField(type, fieldName);
        field.setAccessible(true);

        return new FieldProxy(field);
    }

    private static Field getDeclaredField(Class<?> type, String fieldName) {
        try {
            return type.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public <T> T get(Object instance) {
        try {
            return (T) field.get(instance);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
