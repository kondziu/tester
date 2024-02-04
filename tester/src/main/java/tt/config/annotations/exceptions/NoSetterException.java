package tt.config.annotations.exceptions;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.stream.Collectors;

import tt.config.annotations.Properties;
import tt.config.annotations.PropertyField;

public class NoSetterException extends AnnotationException {

    private static final String MESSAGE_FORMAT = "Field %s of class %s requires a setter for type %s, but none were found. Getters are available for types: %s";

    private static <T extends Properties> String message(PropertyField<T> field) {
        var setters = PropertyField.setters.keySet().stream()
            .map(c -> c.getName())
            .collect(Collectors.joining(", "));

        return String.format(MESSAGE_FORMAT, 
            field.field.getName(), 
            field.cls().getCanonicalName(), 
            field.type.getTypeName(), 
            setters);
    }

    // TODO remove
    public NoSetterException(Class<?> cls, Field field, Type type) {
        super(String.format(MESSAGE_FORMAT, field.getName(), cls.getSimpleName(), type,
        PropertyField.setters.keySet().stream()
                        .map(c -> c.getName())
                        .collect(Collectors.joining(", "))));
    }

    public <T extends Properties> NoSetterException(PropertyField<T> field, Throwable cause) {
        super(NoSetterException.message(field), cause);
    }

    public <T extends Properties> NoSetterException(PropertyField<T> field) {
        super(NoSetterException.message(field));
    }
}
