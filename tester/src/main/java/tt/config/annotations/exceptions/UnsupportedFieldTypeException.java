package tt.config.annotations.exceptions;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.stream.Collectors;

import tt.config.annotations.Properties;
import tt.config.annotations.PropertyField;

public class UnsupportedFieldTypeException extends AnnotationException {
    private static final String MESSAGE_FORMAT = "Field %s from class %s has type %s, which is unsupported by annotation @%s. Allowed types: %s";

    private static <T extends Properties> String message(PropertyField<T> field) {
        var types = PropertyField.setters.keySet().stream()
            .map(c -> c.getName())
            .collect(Collectors.joining(", "));

        return String.format(MESSAGE_FORMAT, 
            field.field.getName(), 
            field.cls().getCanonicalName(), 
            field.type.getTypeName(), 
            field.option.getClass().getCanonicalName(),
            types);
    }

    // TODO remove
    public UnsupportedFieldTypeException(Class<?> cls, Field field, Type type, Class<?> annotation) {
        super(
                String.format(MESSAGE_FORMAT,
                        field.getName(),
                        cls.getName(),
                        type,
                        annotation.getSimpleName(),
                        PropertyField.setters.keySet().stream()
                                .map(c -> c.getName())
                                .collect(Collectors.joining(", "))));
    }

    public <T extends Properties> UnsupportedFieldTypeException(PropertyField<T> field, Throwable cause) {
        super(UnsupportedFieldTypeException.message(field), cause);
    }

    public <T extends Properties> UnsupportedFieldTypeException(PropertyField<T> field) {
        super(UnsupportedFieldTypeException.message(field));
    }
}
